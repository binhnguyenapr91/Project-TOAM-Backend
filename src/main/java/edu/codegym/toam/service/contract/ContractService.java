package edu.codegym.toam.service.contract;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.ContractStatus;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.repository.AccountRepository;
import edu.codegym.toam.repository.ContractRepository;
import edu.codegym.toam.repository.ContractStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ContractService implements IContractService {
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    ContractStatusRepository contractStatusRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Iterable<Contracts> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contracts findById(Long id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public Contracts update(Contracts contracts) {
        return contractRepository.save(contracts);
    }

    @Override
    public void removeById(Long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public Contracts create(Contracts contracts) {
        ContractStatus contractStatus = contractStatusRepository.findById((long) 1).get();
        contracts.setContractStatus(contractStatus);
        return contractRepository.save(contracts);
    }

    @Override
    public Iterable<Contracts> findAllContractsByHostId(Long id) {
        return contractRepository.findContractsByProperties_Host_Id(id);
    }

    @Override
    public Iterable<Contracts> findAllContractsByRenterId(Long id) {
        return contractRepository.findContractsByRenter_Id(id);
    }

    @Override
    public Iterable<Contracts> findAllContractsByRenterIdAndPropertyId(Long renterId, Long propertyId) {
        return contractRepository.findContractsByRenter_IdAndProperties_Id(renterId, propertyId);
    }

    @Override
    public Float getContractValueById(Long contractId) {
        Contracts contracts = contractRepository.findById(contractId).get();
        return contracts.getContractValue();
    }

    @Override
    public Float getHostValueById(Long hostId) {
        Iterable<Contracts> allContracts = contractRepository.findContractsByProperties_Host_Id(hostId);
        Float hostValue = 0f;
        for (Contracts contracts : allContracts) {
            hostValue += contracts.getContractValue();
        }
        return hostValue;
    }

//    @Override
//    public Float getValueLastMonth(Long hostId) {
//
//        Account host=accountRepository.findById(hostId).get();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
//        System.out.println(now);
//
//        Date beforeOneMonth = new Date();
//
//        Calendar cal = Calendar.getInstance();
//        now = cal.getTime();
//        cal.add(Calendar.MONTH, -1);
//        beforeOneMonth = cal.getTime();
//
//
//        System.out.println(host.getCreatedDate());
//
//        Iterable<Contracts> contracts
//                = contractRepository.findContractsByProperties_Host_IdAndCreateTimeBetween(hostId,now,beforeOneMonth);
//
//        Float monthValue = 0f;
//        for (Contracts contract : contracts) {
//            monthValue = +contract.getContractValue();
//        }
//        return monthValue;
//    }

    @Override
    public Float getValueLastMonth(Long hostId) {

        Account host = accountRepository.findById(hostId).get();

        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime beforeDate = nowDate.minusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = nowDate.format(formatter);
        String beforeOneMonth = beforeDate.format(formatter);

        Date nowSql = java.sql.Date.valueOf(now);
        Date beforeOneMonthSql = java.sql.Date.valueOf(beforeOneMonth);
        System.out.println(nowSql);
        System.out.println(beforeOneMonthSql);


        Iterable<Contracts> contracts
                = contractRepository.findAllByCreateTimeBetweenAndProperties_Host_Id(beforeOneMonthSql, nowSql, hostId);
        int quantity = 0;
        Float monthValue = 0f;
        for (Contracts contract : contracts) {
            System.out.println(contract.getContractValue());
            monthValue += contract.getContractValue();

            quantity++;
        }
        System.out.println(quantity);
        return monthValue;
    }

}
