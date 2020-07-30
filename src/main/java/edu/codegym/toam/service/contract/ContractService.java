package edu.codegym.toam.service.contract;

import edu.codegym.toam.ValuePerMonth;
import edu.codegym.toam.exception.ContractException;
import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.ContractStatus;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.model.LeaseTerm;
import edu.codegym.toam.repository.AccountRepository;
import edu.codegym.toam.repository.ContractRepository;
import edu.codegym.toam.repository.ContractStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Component
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

    @Override
    public Iterable<ValuePerMonth> getHistory(Long hostId) {
        Account host = accountRepository.findById(hostId).get();
        Date createdDate = host.getCreatedDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        now = cal.getTime();
        List<ValuePerMonth> valuePerMonthsList = new ArrayList<ValuePerMonth>();
        while (createdDate.before(now) || createdDate.equals(now)) {
            Integer month = createdDate.getMonth() + 1;
            Iterable<Contracts> contracts = contractRepository
                    .findHistoryContractPerMonth(month, hostId);
            ValuePerMonth valuePerMonth = new ValuePerMonth();
            int quantity = 0;
            float value = 0;
            for (Contracts contract : contracts
            ) {
                quantity++;
                value += contract.getContractValue();
            }
            valuePerMonth.setQuantityOfContracts(quantity);
            valuePerMonth.setDateAndMonth((createdDate.getMonth() + 1) + "/" + (createdDate.getYear() + 1900));
            valuePerMonth.setValuePerMonth(value);
            if (quantity > 0)
                valuePerMonthsList.add(valuePerMonth);

            createdDate.setMonth(createdDate.getMonth() + 1);
        }
        return valuePerMonthsList;
    }

    @Override
    public List<ValuePerMonth> findAllContractsHistory(Long hostId) {
        return null;
    }

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

        Iterable<Contracts> contracts
                = contractRepository.findAllByCreateTimeBetweenAndProperties_Host_Id(beforeOneMonthSql, nowSql, hostId);
        int quantity = 0;
        Float monthValue = 0f;
        for (Contracts contract : contracts) {
            System.out.println(contract.getContractValue());
            monthValue += contract.getContractValue();

            quantity++;
        }
        return monthValue;
    }


    public boolean checkContractTime(Date currentTime, Date checkinTime, Date checkoutTime) throws ContractException {
        if (checkinTime.before(currentTime) || checkoutTime.before(checkinTime)) {
            throw new ContractException();
        } else {
            return true;
        }
    }

    @Override
    public boolean checkAvailableTime(Date checkinTime, Long id) throws ContractException {
        Iterable<Contracts> contractsByPropertyId = contractRepository.findContractsByPropertiesId(id);
        List<LeaseTerm> leaseTermList = null;
        if (contractsByPropertyId != null) {
            for (Contracts item : contractsByPropertyId) {
                leaseTermList.add(new LeaseTerm(item.getBeginTime(), item.getEndTime()));
            }
        }
        return false;
    }

    public Long getLeaseTerm(Date checkinTime, Date checkoutTime) {
        Long leaseTerm = checkoutTime.getTime() - checkinTime.getTime();
        return leaseTerm / (24 * 60 * 60 * 1000);
    }

    @Override
    public boolean checkContractCancel(Long contractId) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, +1);
        now = c.getTime();
        Contracts contract = contractRepository.findById(contractId).get();
        Date beginTime = contract.getBeginTime();
        return now.before(beginTime);
    }

}
