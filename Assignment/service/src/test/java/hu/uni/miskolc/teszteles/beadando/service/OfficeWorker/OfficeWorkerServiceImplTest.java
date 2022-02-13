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
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OfficeWorkerServiceImplTest {

    @Mock
    OfficeWorkerRepository officeWorkersRepository;

    @InjectMocks
    OfficeWorkerService service;

    private List<OfficeWorkerModel> officeWorkerList;
    private OfficeWorkerModel officeWorker;



    @Before
    public void setUp() throws Exception {
        officeWorkerList = new ArrayList<>();
        officeWorkersRepository = Mockito.mock(OfficeWorkerRepository.class);
        service = new OfficeWorkerServiceImpl(officeWorkersRepository);
        officeWorker = new OfficeWorkerModel(1L,"János Béla", "béla@gmail.com", true, LocalDate.of(1990,10,11), WorkStatus.FULLTIME, 1000);
    }

    @Test
    public void getAllWorkerTest(){
        doReturn(officeWorkerList).when(officeWorkersRepository).findAll();
        List<OfficeWorkerModel> actual = service.getAllWorker();
        assertEquals(actual, service.getAllWorker());
        verify(officeWorkersRepository, times(2)).findAll();
    }

    @Test
    public void getWorkerByName_WithGoodData(){
        officeWorkerList.add(officeWorker);
        when(officeWorkersRepository.findOfficeWorkerModelByName(officeWorker.getName())).thenReturn(officeWorkerList);
        assertEquals(officeWorker, service.getWorkerByName(officeWorker.getName()));
        verify(officeWorkersRepository, times(1)).findOfficeWorkerModelByName(officeWorker.getName());
    }


    @Test(expected = NotExistingWorker.class)
    public void getWorkerByName_WithNotGoodData1(){
        when(officeWorkersRepository.findOfficeWorkerModelByName("random név")).thenReturn(null);
        assertEquals(officeWorker, service.getWorkerByName(officeWorker.getName()));
        verify(officeWorkersRepository, times(1)).findOfficeWorkerModelByName(officeWorker.getName());
    }

    @Test(expected = IdIsNotAppropriate.class)
    public void addWorkTest_WithNotGoodIdData() throws IdIsNotAppropriate, EmailIsUsed {
        when(officeWorkersRepository.findById(officeWorker.getId())).thenReturn(java.util.Optional.ofNullable(officeWorker));
        service.addWorker(officeWorker);
        verify(officeWorkersRepository, times(1)).findById(officeWorker.getId());
    }

    @Test(expected = EmailIsUsed.class)
    public void addWorkTest_WithNotGoodEmailData() throws IdIsNotAppropriate, EmailIsUsed {
        when(officeWorkersRepository.findById(officeWorker.getId())).thenReturn(Optional.ofNullable(officeWorker));
        when(officeWorkersRepository.findOfficeWorkerModelByEmail(officeWorker.getEmail())).thenReturn(java.util.Optional.ofNullable(officeWorker));

        service.addWorker(officeWorker);

        verify(officeWorkersRepository, times(1)).findById(officeWorker.getId());
        verify(officeWorkersRepository, times(1)).findOfficeWorkerModelByEmail(officeWorker.getEmail());
    }

    @Test
    public void addWorkTest_WithGoodData() throws IdIsNotAppropriate, EmailIsUsed {
        when(officeWorkersRepository.findById(officeWorker.getId())).thenReturn(Optional.empty());
        when(officeWorkersRepository.findOfficeWorkerModelByEmail(officeWorker.getEmail())).thenReturn(Optional.empty());

        service.addWorker(officeWorker);

        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository, times(1)).findById(officeWorker.getId());
        verify(officeWorkersRepository, times(1)).findOfficeWorkerModelByEmail(officeWorker.getEmail());
    }

    @Test
    public void updateWorker_WithGoodData() throws NotValidEmailFormat, TooLowSalary, AgeNotAppropriate {
        when(officeWorkersRepository.findById(officeWorker.getId())).thenReturn(java.util.Optional.ofNullable(officeWorker));
        officeWorker.setAdmin(false);
        officeWorker.setEmail("pista@gmail.com");
        service.updateWorker(officeWorker);
        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository, times(1)).findById(officeWorker.getId());
    }

    @Test(expected = NotExistingWorker.class)
    public void updateWorker_WithNotGoodIdData() throws NotValidEmailFormat, TooLowSalary, AgeNotAppropriate {
        when(officeWorkersRepository.findById(officeWorker.getId())).thenThrow(new NotExistingWorker(officeWorker.toString()));

        officeWorker.setAdmin(false);
        officeWorker.setEmail("pista@gmail.com");
        service.updateWorker(officeWorker);

        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository, times(1)).findById(officeWorker.getId());
    }

    @Test
    public void deleteWorkerByIdTest_WithGoodData() throws IdIsNotAppropriate {
        when(officeWorkersRepository.findById(officeWorker.getId())).thenReturn(java.util.Optional.ofNullable(officeWorker));
        service.deleteWorker(officeWorker.getId());
        verify(officeWorkersRepository, times(1)).deleteById(officeWorker.getId());
    }

    @Test(expected = IdIsNotAppropriate.class)
    public void deleteWorkerByIdTest_WithNotGoodData() throws IdIsNotAppropriate {
        when(officeWorkersRepository.findById(officeWorker.getId())).thenReturn(null);
        service.deleteWorker(officeWorker.getId());
        verify(officeWorkersRepository, times(1)).deleteById(officeWorker.getId());
    }

    @Test
    public void employeeOfTheMonthTest_WithGoodData() throws TooLowSalary {
        officeWorkerList.add(officeWorker);
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        when(officeWorkersRepository.findAll()).thenReturn(officeWorkerList);
        String result = service.employeeOfTheMonth();
        String excepted = "In "+currentMonth+" the employee of the month: "+officeWorker.getName();

        assertEquals(excepted, result);
        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository,times(1)).findAll();
    }

    @Test
    public void employeeOfTheMonthTest_WithEmptyList() throws TooLowSalary {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        when(officeWorkersRepository.findAll()).thenReturn(officeWorkerList);
        String result = service.employeeOfTheMonth();

        String excepted = "There is no employee";

        assertEquals(excepted, result);
        verify(officeWorkersRepository,times(1)).findAll();
    }

    @Test(expected = TooLowSalary.class)
    public void employeeOfTheMonthTest_WithTooLowSalary() throws TooLowSalary {
        officeWorker.setSalary(0);

        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();

        when(officeWorkersRepository.findAll()).thenReturn(officeWorkerList);

        if(officeWorker.getSalary()< 700) {
            when(officeWorkersRepository.save(officeWorker)).thenThrow(new TooLowSalary(officeWorker.getSalary()));
        }

        String result = service.employeeOfTheMonth();

        String excepted = "In "+currentMonth+" the employee of the month: "+officeWorker.getName();
        assertEquals(excepted, result);

        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository,times(1)).findAll();
    }

    @Test
    public void employeeFirer_WithGoodData(){
        officeWorkerList.add(officeWorker);
        when(officeWorkersRepository.findAll()).thenReturn(officeWorkerList);
        service.employeeFirer();
        verify(officeWorkersRepository, times(1)).findAll();
        verify(officeWorkersRepository, times(1)).deleteById(officeWorkerList.get(0).getId());
    }

    @Test(expected = NoEmployee.class)
    public void employeeFirer_WithEmptyList(){
        when(officeWorkersRepository.findAll()).thenReturn(officeWorkerList);
        service.employeeFirer();
        verify(officeWorkersRepository, times(1)).findAll();
    }

    @Test
    public void changeSalaryById_WithGoodData() throws TooLowSalary {
        officeWorkerList.add(officeWorker);
        when(officeWorkersRepository.findById(1L)).thenReturn(Optional.ofNullable(officeWorkerList.get(0)));
        service.changeSalaryById(1L, 1000);
        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository,times(1)).findById(1L);
    }

    @Test(expected = TooLowSalary.class)
    public void changeSalaryById_WithTooLowSalary() throws TooLowSalary {
        officeWorkerList.add(officeWorker);
        when(officeWorkersRepository.findById(1L)).thenReturn(Optional.ofNullable(officeWorkerList.get(0)));
        service.changeSalaryById(1L, 0);
        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository,times(1)).findById(1L);
    }

    @Test(expected = NotExistingWorker.class)
    public void changeSalaryById_WithNotExistingWorker() throws TooLowSalary {
        Long randomId = 123L;
        when(officeWorkersRepository.findById(randomId)).thenThrow(new NotExistingWorker(officeWorker.toString()));
        service.changeSalaryById(randomId, 0);
        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository,times(1)).findById(randomId);
    }

    @Test
    public void changeWorkStatusById_WithGoodData(){
        officeWorkerList.add(officeWorker);
        when(officeWorkersRepository.findById(1L)).thenReturn(Optional.ofNullable(officeWorkerList.get(0)));
        service.changeWorkStatusById(1L, WorkStatus.CONTRACT);
        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository,times(1)).findById(1L);
    }

    @Test(expected = NotExistingWorker.class)
    public void changeWorkStatusById_WithNotGoodData(){
        Long randomId = 123L;
        when(officeWorkersRepository.findById(randomId)).thenThrow(new NotExistingWorker(officeWorker.toString()));
        service.changeWorkStatusById(randomId, WorkStatus.CONTRACT);
        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository,times(1)).findById(randomId);
    }

    @Test
    public void getAllWorkerByStatusAndRaiseTheSalary_WithGoodData() throws TooLowSalary, NoEmployee {
        double originalSalary = officeWorker.getSalary();
        officeWorkerList.add(officeWorker);

        OfficeWorkerModel officeWorker2 = new OfficeWorkerModel(2L,"Kis Pista", "pista@gmail.com", false, LocalDate.of(2000,10,20), WorkStatus.FULLTIME, 1300);
        double originalSalary2 = officeWorker2.getSalary();
        officeWorkerList.add(officeWorker2);

        when(officeWorkersRepository.findOfficeWorkerModelByStatus(officeWorker.getStatus())).thenReturn((List<OfficeWorkerModel>) officeWorkerList);

        double salaryBonus = 50;
        service.getAllWorkerByStatusAndRaiseTheSalary(officeWorker.getStatus(), salaryBonus);

        assertEquals(2, officeWorkerList.size());
        assertEquals(originalSalary+salaryBonus, officeWorkerList.get(0).getSalary(), 0.001);
        assertEquals(originalSalary2+salaryBonus, officeWorkerList.get(1).getSalary(), 0.001);

        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository, times(1)).save(officeWorker2);
        verify(officeWorkersRepository, times(1)).findOfficeWorkerModelByStatus(officeWorker.getStatus());
    }

    @Test
    public void getAllWorkerByStatusAndRaiseTheSalary_WithGoodData2() throws TooLowSalary, NoEmployee {
        double originalSalary = officeWorker.getSalary();
        officeWorkerList.add(officeWorker);

        OfficeWorkerModel officeWorker2 = new OfficeWorkerModel(2L,"Kis Pista", "pista@gmail.com", false, LocalDate.of(2000,10,20), WorkStatus.PARTTIME, 1300);
        double originalSalary2 = officeWorker2.getSalary();
        officeWorkerList.add(officeWorker2);

        when(officeWorkersRepository.findOfficeWorkerModelByStatus(officeWorker.getStatus())).thenReturn((List<OfficeWorkerModel>) officeWorkerList);

        double salaryBonus = 50;
        service.getAllWorkerByStatusAndRaiseTheSalary(officeWorker.getStatus(), salaryBonus);

        assertEquals(2, officeWorkerList.size());
        assertEquals(originalSalary+salaryBonus, officeWorkerList.get(0).getSalary(), 0.001);
        assertEquals(originalSalary2, officeWorker2.getSalary(), 0.001);

        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository, times(1)).findOfficeWorkerModelByStatus(officeWorker.getStatus());
    }

    @Test(expected = TooLowSalary.class)
    public void getAllWorkerByStatusAndRaiseTheSalary_WithTooLowSalary() throws TooLowSalary, NoEmployee {
        double originalSalary = officeWorker.getSalary();
        officeWorkerList.add(officeWorker);

        OfficeWorkerModel officeWorker2 = new OfficeWorkerModel(2L,"Kis Pista", "pista@gmail.com", false, LocalDate.of(2000,10,20), WorkStatus.FULLTIME, 1300);
        double originalSalary2 = officeWorker2.getSalary();
        officeWorkerList.add(officeWorker2);

        when(officeWorkersRepository.findOfficeWorkerModelByStatus(officeWorker.getStatus())).thenReturn((List<OfficeWorkerModel>) officeWorkerList);

        double salaryBonus = -1000;
        service.getAllWorkerByStatusAndRaiseTheSalary(officeWorker.getStatus(), salaryBonus);

        assertEquals(2, officeWorkerList.size());
        assertEquals(originalSalary+salaryBonus, officeWorkerList.get(0).getSalary(), 0.001);
        assertEquals(originalSalary2+salaryBonus, officeWorkerList.get(1).getSalary(), 0.001);

        verify(officeWorkersRepository, times(1)).save(officeWorker);
        verify(officeWorkersRepository, times(1)).save(officeWorker2);
        verify(officeWorkersRepository, times(1)).findOfficeWorkerModelByStatus(officeWorker.getStatus());
    }

    @Test(expected = NoEmployee.class)
    public void getAllWorkerByStatusAndRaiseTheSalary_WithEmptyList() throws TooLowSalary, NoEmployee {
        when(officeWorkersRepository.findOfficeWorkerModelByStatus(officeWorker.getStatus())).thenReturn((List<OfficeWorkerModel>) officeWorkerList);

        double salaryBonus = 1000;
        service.getAllWorkerByStatusAndRaiseTheSalary(officeWorker.getStatus(), salaryBonus);

    }

}