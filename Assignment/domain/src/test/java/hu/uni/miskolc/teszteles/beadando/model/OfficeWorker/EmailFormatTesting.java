package hu.uni.miskolc.teszteles.beadando.model.OfficeWorker;

import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions.NotValidEmailFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class EmailFormatTesting {
    @Parameterized.Parameters
    public static Collection data(){
        List data = new ArrayList<>();
        data.add(new Object[] {""});
        data.add(new Object[] {"fdéljgfélkédgé"});
        data.add(new Object[] {"béla"});
        data.add(new Object[] {"béla@fsfsf"});
        data.add(new Object[] {"pist@fsgf.s"});
        data.add(new Object[] {"pIsta456@lkhsglhlcom"});
        data.add(new Object[] {"emailemail.com"});
        data.add(new Object[] {"format.format@com"});
        return data;
    }

    String emailAddress;

    public EmailFormatTesting(String emailAddress){
        this.emailAddress = emailAddress;
    }

    @Test(expected = NotValidEmailFormat.class)
    public void testNotValidEmailFormats() throws NotValidEmailFormat {
        OfficeWorkerModel worker = new OfficeWorkerModel();
        System.out.println("Email address:" + emailAddress);
        worker.setEmail(emailAddress);
    }

}
