package hu.uni.miskolc.teszteles.beadando.service.OfficeWorker;

import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.OfficeWorkerModel;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.enums.WorkStatus;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.AgeNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.NotValidEmailFormat;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.TooLowSalary;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.EmailIsUsed;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.IdIsNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.NoEmployee;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.NotExistingWorker;

import java.util.List;
import java.util.Optional;

public interface OfficeWorkerService {

    List<OfficeWorkerModel> getAllWorker();
    OfficeWorkerModel getWorkerByName(String name) throws NotExistingWorker;
    void addWorker(OfficeWorkerModel worker) throws IdIsNotAppropriate, EmailIsUsed;
    void employeeFirer() throws NoEmployee;
    String employeeOfTheMonth() throws TooLowSalary;
    void deleteWorker(Long id) throws IdIsNotAppropriate;
    void updateWorker(OfficeWorkerModel worker) throws AgeNotAppropriate, NotValidEmailFormat, TooLowSalary;
    void changeSalaryById(Long id, double salary) throws TooLowSalary;
    void changeWorkStatusById(Long id, WorkStatus status);
    List<OfficeWorkerModel> getAllWorkerByStatusAndRaiseTheSalary(WorkStatus status, double salaryBonus) throws TooLowSalary, NoEmployee;
}
