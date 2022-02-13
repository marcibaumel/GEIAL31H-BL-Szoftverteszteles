package hu.uni.miskolc.teszteles.beadando.service.OfficeWorker;

import hu.uni.miskolc.teszteles.beadando.dao.OfficeWorker.OfficeWorkerRepository;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.OfficeWorkerModel;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.enums.WorkStatus;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.AgeNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.NotValidEmailFormat;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.TooLowSalary;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.EmailIsUsed;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.IdIsNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.NoEmployee;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.NotExistingWorker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeWorkerServiceImpl implements OfficeWorkerService{

    private final OfficeWorkerRepository officeWorkerRepository;

    public OfficeWorkerServiceImpl(OfficeWorkerRepository officeWorkerRepository) {
        this.officeWorkerRepository = officeWorkerRepository;
    }

    @Override
    public List<OfficeWorkerModel> getAllWorker(){
        return (List<OfficeWorkerModel>) officeWorkerRepository.findAll();
    }

    @Override
    public OfficeWorkerModel getWorkerByName(String name) throws NotExistingWorker{
        return officeWorkerRepository.findOfficeWorkerModelByName(name).stream().findFirst().orElseThrow(() -> new NotExistingWorker(
                String.format("Cannot find worker by this name %s", name)
        ));
    }


    //2DAO
    @Override
    public void deleteWorker(Long id) throws IdIsNotAppropriate {
        if(officeWorkerRepository.findById(id) == null) {
            throw new IdIsNotAppropriate(Math.toIntExact(id));
        }
        else{
            officeWorkerRepository.deleteById(id);
        }
    }

    //2DAO
    @Override
    public void updateWorker(OfficeWorkerModel worker) throws AgeNotAppropriate, NotValidEmailFormat, TooLowSalary {

        OfficeWorkerModel savedOfficeWorker = officeWorkerRepository.findById(worker.getId()).orElseThrow(() -> new NotExistingWorker(worker.getName()));

        savedOfficeWorker.setName(worker.getName());
        savedOfficeWorker.setBirthday(worker.getBirthday());
        savedOfficeWorker.setEmail(worker.getEmail());
        savedOfficeWorker.setStatus(worker.getStatus());
        savedOfficeWorker.setAdmin(worker.isAdmin());
        savedOfficeWorker.setSalary(worker.getSalary());

        officeWorkerRepository.save(savedOfficeWorker);
    }

    //3DAO
    @Override
    public void addWorker(OfficeWorkerModel worker) throws IdIsNotAppropriate, EmailIsUsed {

        Optional<OfficeWorkerModel> workerEmailCheck = officeWorkerRepository.findOfficeWorkerModelByEmail(worker.getEmail());
        Optional<OfficeWorkerModel> workerIdCheck = officeWorkerRepository.findById(worker.getId());

        if(!workerEmailCheck.isEmpty()) {
            throw new EmailIsUsed(worker.getEmail());
        }

        if(!workerIdCheck.isEmpty()){
            throw new IdIsNotAppropriate(Math.toIntExact(worker.getId()));
        }

        officeWorkerRepository.save(worker);
    }

    //2DAO
    @Override
    public void employeeFirer() throws NoEmployee {
        List<OfficeWorkerModel> officeWorkers = (List<OfficeWorkerModel>) officeWorkerRepository.findAll();

        if(officeWorkers.size() == 0){
            throw new NoEmployee();
        }
        int employeeIndex = (int) (Math.random()*(officeWorkers.size()));
        officeWorkerRepository.deleteById(officeWorkers.get(employeeIndex).getId());
    }

    //2DAO
    @Override
    public String employeeOfTheMonth() throws TooLowSalary {
        String result = "";

        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        List<OfficeWorkerModel> officeWorkers = (List<OfficeWorkerModel>) officeWorkerRepository.findAll();

        if(officeWorkers.size() == 0){
            return "There is no employee";
        }
        int sizeOfTheWorkers = officeWorkers.size();
        int employeeOfTheMonthIndex = (int) (Math.random()*(sizeOfTheWorkers));
        OfficeWorkerModel employeeOfTheMonth = officeWorkers.get(employeeOfTheMonthIndex);

        //Bonus
        int givenBonus = 50;
        employeeOfTheMonth.setSalary(employeeOfTheMonth.getSalary() + givenBonus);

        officeWorkerRepository.save(employeeOfTheMonth);

        result = "In "+currentMonth+" the employee of the month: "+employeeOfTheMonth.getName();
        return result;
    }

    //change the salary by id
    //2DAO
    @Override
    public void changeSalaryById(Long id, double salary) throws TooLowSalary {
        OfficeWorkerModel savedOfficeWorker = officeWorkerRepository.findById(id).orElseThrow(() -> new NotExistingWorker("Not existing: "+id.toString()));
        savedOfficeWorker.setSalary(salary);
        officeWorkerRepository.save(savedOfficeWorker);
    }

    //change the work status by id
    //2DAO
    @Override
    public void changeWorkStatusById(Long id, WorkStatus status)  {
        OfficeWorkerModel savedOfficeWorker = officeWorkerRepository.findById(id).orElseThrow(() -> new NotExistingWorker("Not existing: "+id.toString()));
        savedOfficeWorker.setStatus(status);
        officeWorkerRepository.save(savedOfficeWorker);
    }

    //2DAO
    @Override
    public List<OfficeWorkerModel> getAllWorkerByStatusAndRaiseTheSalary(WorkStatus status, double salaryBonus) throws TooLowSalary, NoEmployee {
        List<OfficeWorkerModel> officeWorkerModelList = officeWorkerRepository.findOfficeWorkerModelByStatus(status);
        if(!officeWorkerModelList.isEmpty()) {
            for (OfficeWorkerModel listElement : officeWorkerModelList) {
                if (listElement.getStatus() == status) {
                    listElement.setSalary(listElement.getSalary() + salaryBonus);
                    officeWorkerRepository.save(listElement);
                }
            }
        }
        else {
            throw new NoEmployee();
        }
        return officeWorkerModelList;
    }

}
