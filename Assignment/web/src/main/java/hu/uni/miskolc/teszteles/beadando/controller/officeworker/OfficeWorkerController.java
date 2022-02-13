package hu.uni.miskolc.teszteles.beadando.controller.officeworker;

import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.OfficeWorkerModel;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.enums.WorkStatus;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.AgeNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.NotValidEmailFormat;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.TooLowSalary;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.OfficeWorkerService;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.EmailIsUsed;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.IdIsNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.NoEmployee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/office/workers")
public class OfficeWorkerController {

    private final OfficeWorkerService officeWorkerService;


    public OfficeWorkerController(OfficeWorkerService officeWorkerService) {
        this.officeWorkerService = officeWorkerService;
    }

    @PostMapping(path = "")
    public void addWorker(@RequestBody OfficeWorkerModel worker) throws IdIsNotAppropriate, EmailIsUsed {
        officeWorkerService.addWorker(worker);
    }


    @PutMapping
    public void updateWorker(@RequestBody OfficeWorkerModel worker) throws NotValidEmailFormat, TooLowSalary, AgeNotAppropriate {
        officeWorkerService.updateWorker(worker);
    }

    @GetMapping(path = "/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<OfficeWorkerModel> getAllWorker(){
        return officeWorkerService.getAllWorker();
    }

    @GetMapping("/{name}")
    public OfficeWorkerModel getWorkerByName(@PathVariable String name){
        return officeWorkerService.getWorkerByName(name);
    }

    @GetMapping("/employeeOfTheMonth")
    public String getEmployeeOfTheMonth() throws TooLowSalary {
        return officeWorkerService.employeeOfTheMonth();
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id) throws IdIsNotAppropriate {
        officeWorkerService.deleteWorker(id);
    }

    @DeleteMapping("/fire")
    public void fireARandomWorker() throws NoEmployee {
        officeWorkerService.employeeFirer();
    }

    @PutMapping("changeSalaryById/{id}/{salary}")
    public void changeSalaryById(@PathVariable Long id, @PathVariable double salary) throws TooLowSalary {
        officeWorkerService.changeSalaryById(id, salary);
    }

    @PutMapping("changeWorkStatusById/{id}/{status}")
    public void changeWorkStatusById(@PathVariable Long id, @PathVariable WorkStatus status) {
        officeWorkerService.changeWorkStatusById(id, status);
    }

    @PutMapping("getAllWorkerByStatusAndRaiseTheSalary/{status}/{salaryBonus}")
    public List<OfficeWorkerModel> getAllWorkerByStatusAndRaiseTheSalary(@PathVariable WorkStatus status, @PathVariable double salaryBonus) throws TooLowSalary {
        return officeWorkerService.getAllWorkerByStatusAndRaiseTheSalary(status, salaryBonus);
    }
}
