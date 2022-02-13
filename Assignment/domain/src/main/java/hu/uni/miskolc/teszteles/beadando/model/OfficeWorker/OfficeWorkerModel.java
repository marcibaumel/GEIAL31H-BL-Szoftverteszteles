package hu.uni.miskolc.teszteles.beadando.model.OfficeWorker;

import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.enums.WorkStatus;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.AgeNotAppropriate;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.NotValidEmailFormat;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.TooLowSalary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class OfficeWorkerModel {
    @Id
    @GeneratedValue
    private Long id;

    //A worker name
    private String name;

    //Regex
    private String email;

    //The worker has admin rights
    private boolean admin;

    //The worker birthday (if it's higher than 70 year or lower than 18 throw exception)
    private LocalDate birthday;

    //Describe the working status
    private WorkStatus status;

    //A worker salary, minimum 700
    private double salary;


    //Full Constructor
    public OfficeWorkerModel(Long id, String name, String email, boolean admin, LocalDate birthday, WorkStatus status, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.admin = admin;
        this.birthday = birthday;
        this.status = status;
        this.salary = salary;
    }


    public OfficeWorkerModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws NotValidEmailFormat {
        String regex ="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"+"[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!email.matches(regex)){
            throw new NotValidEmailFormat(email);
        }
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) throws AgeNotAppropriate {
        int calculatedAge = calculateAge(birthday);
        if(calculatedAge<18 || calculatedAge>70){
            throw new AgeNotAppropriate(birthday);
        }
        this.birthday = birthday;
    }

    public WorkStatus getStatus() {
        return status;
    }

    public void setStatus(WorkStatus status) {
        this.status = status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws TooLowSalary {
        if(salary<700){
            throw new TooLowSalary(salary);
        }
        this.salary = salary;
    }

    public int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
