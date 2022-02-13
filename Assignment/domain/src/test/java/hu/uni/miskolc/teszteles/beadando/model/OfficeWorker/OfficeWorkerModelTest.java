package hu.uni.miskolc.teszteles.beadando.model.OfficeWorker;

import static org.junit.Assert.*;

import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.enums.WorkStatus;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.AgeNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.NotValidEmailFormat;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.TooLowSalary;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class OfficeWorkerModelTest {
    private OfficeWorkerModel officeWorker;

    @Before
    public void setUp(){
        officeWorker = new OfficeWorkerModel();
    }

    @Test
    public void fullConstructorTest(){
        Long id = 1L;
        String name = "Nagy Lajos";
        String email = "nagylajos@gmail.com";
        LocalDate birthday = LocalDate.of(1980,4,15);
        WorkStatus workStatus = WorkStatus.FULLTIME;
        double salary = 1000;

        officeWorker = new OfficeWorkerModel(id,name,email, true, birthday, workStatus, salary);

        assertEquals(officeWorker.getName(), name);
        assertEquals(officeWorker.getId(), id);
        assertEquals(officeWorker.getEmail(), email);
        assertEquals(officeWorker.getBirthday(), birthday);
        assertEquals(officeWorker.getStatus(), workStatus);
        assertEquals(officeWorker.getSalary(), salary, 0.001);
    }

    @Test
    public void setSalaryTest_WithCorrectData() throws TooLowSalary {
        double actualSalary = 700;
        officeWorker.setSalary(actualSalary);
        assertEquals(officeWorker.getSalary(), actualSalary, 0.001);
    }

    @Test(expected = TooLowSalary.class)
    public void setSalaryTest_WithNotCorrectData() throws TooLowSalary {
        double actualSalary = 10;
        officeWorker.setSalary(actualSalary);
    }

    @Test
    public void setWorkerIdTest(){
        Long actualId = 134L;
        officeWorker.setId(actualId);
        assertEquals(officeWorker.getId(), actualId);
    }


    @Test
    public void setWorkerNameTest(){
        String actualName = "Béla";
        officeWorker.setName(actualName);
        assertEquals(officeWorker.getName(), actualName);
    }

    @Test
    public void setAdminPermission(){
        officeWorker.setAdmin(false);
        assertFalse(officeWorker.isAdmin());
    }

    @Test
    public void setAdminPermission2(){
        officeWorker.setAdmin(true);
        assertTrue(officeWorker.isAdmin());
    }

    @Test
    public void setWorkerStatusTest(){
        officeWorker.setStatus(WorkStatus.CONTRACT);
        assertEquals(officeWorker.getStatus(), WorkStatus.CONTRACT);
    }

    @Test
    public void SideCalculateAgeTest_WithCorrectData(){
        LocalDate actualDate = LocalDate.of(1990,10,20);
        int actualAge = officeWorker.calculateAge(actualDate);
        assertEquals(31, actualAge);
    }

    @Test
    public void SideCalculateAgeTest_WithNotCorrectLocalDate(){
        int actualAge = officeWorker.calculateAge(null);
        assertEquals(0, actualAge);
    }


    @Test
    public void setEmailTest_WithCorrectData() throws NotValidEmailFormat {
        String actualEmail = "marciemail@gmail.com";
        officeWorker.setEmail(actualEmail);
        assertEquals(actualEmail, officeWorker.getEmail());
    }

    @Test(expected = NotValidEmailFormat.class)
    public void setEmailTest_WithNotCorrectData1() throws NotValidEmailFormat {
        String actualEmail = "marcigmailcom";
        officeWorker.setEmail(actualEmail);
        assertEquals(actualEmail, officeWorker.getEmail());
    }

    @Test(expected = NotValidEmailFormat.class)
    public void setEmailTest_WithNotCorrectData2() throws NotValidEmailFormat {
        String actualEmail = "marci@gmailcom";
        officeWorker.setEmail(actualEmail);
        assertEquals(actualEmail, officeWorker.getEmail());
    }

    @Test(expected = NotValidEmailFormat.class)
    public void setEmailTest_WithNotCorrectData3() throws NotValidEmailFormat {
        String actualEmail = "marcigmail.com";
        officeWorker.setEmail(actualEmail);
        assertEquals(actualEmail, officeWorker.getEmail());
    }

    @Test(expected = NotValidEmailFormat.class)
    public void setEmailTest_WithNotCorrectData4() throws NotValidEmailFormat {
        String actualEmail = "@gmail.com";
        officeWorker.setEmail(actualEmail);
        assertEquals(actualEmail, officeWorker.getEmail());
    }

    @Test(expected = NotValidEmailFormat.class)
    public void setEmailTest_WithNotCorrectData5() throws NotValidEmailFormat {
        String actualEmail = "marci@.com";
        officeWorker.setEmail(actualEmail);
        assertEquals(actualEmail, officeWorker.getEmail());
    }

    @Test(expected = NotValidEmailFormat.class)
    public void setEmailTest_WithNotCorrectData6() throws NotValidEmailFormat {
        String actualEmail = "";
        officeWorker.setEmail(actualEmail);
        assertEquals(actualEmail, officeWorker.getEmail());
    }

    @Test
    public void setBirthday_WithCorrectData() throws AgeNotAppropriate {
        LocalDate actualBirthday = LocalDate.of(1990,10,11);
        officeWorker.setBirthday(actualBirthday);
        assertEquals(actualBirthday, officeWorker.getBirthday());
    }

    @Test(expected = AgeNotAppropriate.class)
    public void setBirthday_WithNotCorrectData_TooOld() throws AgeNotAppropriate {
        LocalDate actualBirthday = LocalDate.of(1890,10,11);
        officeWorker.setBirthday(actualBirthday);
        assertEquals(actualBirthday, officeWorker.getBirthday());
    }

    @Test(expected = AgeNotAppropriate.class)
    public void setBirthday_WithNotCorrectData_TooYoung() throws AgeNotAppropriate {
        LocalDate actualBirthday = LocalDate.of(2015,10,11);
        officeWorker.setBirthday(actualBirthday);
        assertEquals(actualBirthday, officeWorker.getBirthday());
    }

}