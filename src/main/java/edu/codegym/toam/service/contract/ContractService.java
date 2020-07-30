package edu.codegym.toam.service.contract;

import edu.codegym.toam.exception.ContractException;
import edu.codegym.toam.model.ContractStatus;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.model.LeaseTerm;
import edu.codegym.toam.repository.ContractRepository;
import edu.codegym.toam.repository.ContractStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Component
public class ContractService implements IContractService {
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    ContractStatusRepository contractStatusRepository;

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
    public Iterable<Contracts> findAllContractsByRenterIdAndPropertyId(Long renterId,Long propertyId) {
        return contractRepository.findContractsByRenter_IdAndProperties_Id(renterId, propertyId);
    }

    @Override
    public boolean checkContractTime (Date currentTime,Date checkinTime,Date checkoutTime) throws ContractException {
        if(checkinTime.before(currentTime)||checkoutTime.before(checkinTime)){
            throw new ContractException();
        }else{
            return true;
        }
    }

    @Override
    public boolean checkAvailableTime(Date checkinTime, Long id) throws ContractException {
        Iterable<Contracts> contractsByPropertyId = contractRepository.findContractsByPropertiesId(id);
        List<LeaseTerm> leaseTermList = null;
        if (contractsByPropertyId != null){
            for(Contracts item : contractsByPropertyId){
                leaseTermList.add(new LeaseTerm(item.getBeginTime(),item.getEndTime()));
            }
        }
        return false;
    }

    public Long getLeaseTerm (Date checkinTime,Date checkoutTime){
        Long leaseTerm = checkoutTime.getTime()-checkinTime.getTime();
        return leaseTerm / (24*60*60*1000);
    }
}
