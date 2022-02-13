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
import hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions.NotExistingWorker;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OfficeWorkerControllerTest {

    @Mock
    OfficeWorkerService officeWorkerService;

    @InjectMocks
    OfficeWorkerController officeWorkerController;

    private List<OfficeWorkerModel> officeWorkerList;

    private OfficeWorkerModel officeWorker;

    @Before
    public void setUp() throws Exception {
        officeWorkerList = new ArrayList<>();
        officeWorkerService = Mockito.mock(OfficeWorkerService.class);
        officeWorkerController = new OfficeWorkerController(officeWorkerService);
        officeWorker = new OfficeWorkerModel(1L, "János Béla", "béla@gmail.com", true, LocalDate.of(1990, 10, 11), WorkStatus.FULLTIME, 1000);
    }

    @Test
    public void getAllWorkerTest() {
        officeWorkerList.add(officeWorker);
        when(officeWorkerService.getAllWorker()).thenReturn(officeWorkerList);
        List<OfficeWorkerModel> actualList = officeWorkerController.getAllWorker();
        assertEquals(officeWorkerList, actualList);
        verify(officeWorkerService, times(1)).getAllWorker();
    }

    @Test
    public void addWorkerTest_WithGoodData() throws EmailIsUsed, IdIsNotAppropriate {
        officeWorkerController.addWorker(officeWorker);
        verify(officeWorkerService, times(1)).addWorker(officeWorker);
    }

    @Test(expected = EmailIsUsed.class)
    public void addWorkerTest_WithNotGoodData_ExceptionEmailIsUsed() throws EmailIsUsed, IdIsNotAppropriate {
        doThrow(new EmailIsUsed(officeWorker.getEmail())).when(officeWorkerService).addWorker(officeWorker);
        officeWorkerController.addWorker(officeWorker);
        verify(officeWorkerService, times(1)).addWorker(officeWorker);
    }

    @Test(expected = IdIsNotAppropriate.class)
    public void addWorkerTest_WithNotGoodData_ExceptionIdIsNotAppropriate() throws EmailIsUsed, IdIsNotAppropriate {
        doThrow(new IdIsNotAppropriate(Math.toIntExact(officeWorker.getId()))).when(officeWorkerService).addWorker(officeWorker);
        officeWorkerController.addWorker(officeWorker);
        verify(officeWorkerService, times(1)).addWorker(officeWorker);
    }

    @Test
    public void updateWorkerTest_WithGoodData() throws NotValidEmailFormat, TooLowSalary, AgeNotAppropriate {
        officeWorkerController.updateWorker(officeWorker);
        verify(officeWorkerService, times(1)).updateWorker(officeWorker);
    }

    @Test(expected = NotValidEmailFormat.class)
    public void updateWorkerTest_WithNotValidEmailFormat() throws NotValidEmailFormat, TooLowSalary, AgeNotAppropriate {
        doThrow(new NotValidEmailFormat(officeWorker.getEmail())).when(officeWorkerService).updateWorker(officeWorker);
        officeWorkerController.updateWorker(officeWorker);
        verify(officeWorkerService, times(1)).updateWorker(officeWorker);
    }

    @Test(expected = TooLowSalary.class)
    public void updateWorkerTest_TooLowSalary() throws NotValidEmailFormat, TooLowSalary, AgeNotAppropriate {
        doThrow(new TooLowSalary(officeWorker.getSalary())).when(officeWorkerService).updateWorker(officeWorker);
        officeWorkerController.updateWorker(officeWorker);
        verify(officeWorkerService, times(1)).updateWorker(officeWorker);
    }

    @Test(expected = AgeNotAppropriate.class)
    public void updateWorkerTest_AgeNotAppropriate() throws NotValidEmailFormat, TooLowSalary, AgeNotAppropriate {
        doThrow(new AgeNotAppropriate(officeWorker.getBirthday())).when(officeWorkerService).updateWorker(officeWorker);
        officeWorkerController.updateWorker(officeWorker);
        verify(officeWorkerService, times(1)).updateWorker(officeWorker);
    }

    @Test
    public void getWorkerByNameTest() {
        when(officeWorkerService.getWorkerByName(officeWorker.getName())).thenReturn(officeWorker);
        OfficeWorkerModel actualWorker = officeWorkerController.getWorkerByName(officeWorker.getName());
        assertEquals(officeWorker, actualWorker);
        verify(officeWorkerService, times(1)).getWorkerByName(officeWorker.getName());
    }

    @Test
    public void getEmployeeOfTheMonthTest() throws TooLowSalary {
        String expectedResult = "Random employee";
        when(officeWorkerService.employeeOfTheMonth()).thenReturn(expectedResult);
        String result = officeWorkerController.getEmployeeOfTheMonth();
        assertEquals(expectedResult, result);
        verify(officeWorkerService, times(1)).employeeOfTheMonth();
    }

    @Test(expected = TooLowSalary.class)
    public void getEmployeeOfTheMonthTest_TooLowSalary() throws TooLowSalary {
        String expectedResult = "Random employee";
        officeWorker.setSalary(10);
        when(officeWorkerService.employeeOfTheMonth()).thenReturn(expectedResult).thenThrow(new TooLowSalary(officeWorker.getSalary()));
        String result = officeWorkerController.getEmployeeOfTheMonth();
        assertEquals(expectedResult, result);
        verify(officeWorkerService, times(1)).employeeOfTheMonth();
    }

    @Test
    public void deleteWorkerTest() throws IdIsNotAppropriate {
        officeWorkerController.deleteWorker(officeWorker.getId());
        verify(officeWorkerService, times(1)).deleteWorker(officeWorker.getId());
    }

    @Test(expected = IdIsNotAppropriate.class)
    public void deleteWorkerTest_IdIsNotGood() throws IdIsNotAppropriate {
        doThrow(new IdIsNotAppropriate(Math.toIntExact(officeWorker.getId()))).when(officeWorkerService).deleteWorker(officeWorker.getId());
        officeWorkerController.deleteWorker(officeWorker.getId());
        verify(officeWorkerService, times(1)).deleteWorker(officeWorker.getId());
    }

    @Test
    public void fireRandomWorkerTest() {
        officeWorkerController.fireARandomWorker();
        verify(officeWorkerService, times(1)).employeeFirer();
    }

    @Test(expected = NoEmployee.class)
    public void fireRandomWorkerTest_NoEmployee() {
        doThrow(new NoEmployee()).when(officeWorkerService).employeeFirer();
        officeWorkerController.fireARandomWorker();
        verify(officeWorkerService, times(1)).employeeFirer();
    }

    @Test
    public void changeSalaryById_WithGoodData() throws TooLowSalary {
        officeWorkerController.changeSalaryById(1L, 700);
        verify(officeWorkerService, times(1)).changeSalaryById(1L, 700);
    }

    @Test(expected = TooLowSalary.class)
    public void changeSalaryById_WithNotGoodSalary() throws TooLowSalary {
        double salary = -500;
        doThrow(new TooLowSalary(salary)).when(officeWorkerService).changeSalaryById(1L,salary);
        officeWorkerController.changeSalaryById(1L, salary);
        verify(officeWorkerService, times(1)).changeSalaryById(1L, salary);
    }

    @Test(expected = NotExistingWorker.class)
    public void changeSalaryById_WithNotGoodId() throws TooLowSalary {
        double salary = -500;
        doThrow(new NotExistingWorker("wrong user")).when(officeWorkerService).changeSalaryById(1L,salary);
        officeWorkerController.changeSalaryById(1L, salary);
        verify(officeWorkerService, times(1)).changeSalaryById(1L, salary);
    }

    @Test
    public void changeWorkStatusById_WithGoodData(){
        officeWorkerController.changeWorkStatusById(1L, WorkStatus.FULLTIME);
        verify(officeWorkerService, times(1)).changeWorkStatusById(1L, WorkStatus.FULLTIME);
    }

    @Test(expected = NotExistingWorker.class)
    public void changeWorkStatusById_WithNotGoodId() {
        doThrow(new NotExistingWorker("wrong user")).when(officeWorkerService).changeWorkStatusById(1L, WorkStatus.FULLTIME);
        officeWorkerController.changeWorkStatusById(1L, WorkStatus.FULLTIME);
        verify(officeWorkerService, times(1)).changeWorkStatusById(1L, WorkStatus.FULLTIME);
    }

    @Test
    public void getAllWorkerByStatusAndRaiseTheSalary_WithGoodData() throws TooLowSalary {
        double salary = 700;
        WorkStatus status = WorkStatus.FULLTIME;
        officeWorkerController.getAllWorkerByStatusAndRaiseTheSalary(status, salary);
        verify(officeWorkerService, times(1)).getAllWorkerByStatusAndRaiseTheSalary(status, salary);
    }

    @Test(expected = TooLowSalary.class)
    public void getAllWorkerByStatusAndRaiseTheSalary_WithNotGoodSalary() throws TooLowSalary {
        double salary = -700;
        WorkStatus status = WorkStatus.FULLTIME;

        doThrow(new TooLowSalary(salary)).when(officeWorkerService).getAllWorkerByStatusAndRaiseTheSalary(status, salary);

        officeWorkerController.getAllWorkerByStatusAndRaiseTheSalary(status, salary);
        verify(officeWorkerService, times(1)).getAllWorkerByStatusAndRaiseTheSalary(status, salary);
    }
}