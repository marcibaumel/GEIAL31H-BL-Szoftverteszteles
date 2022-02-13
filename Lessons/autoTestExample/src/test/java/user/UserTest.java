package user;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock DatabaseConnection databaseConnection;
    @InjectMocks User u;

    @Test
    public void sumTest(){
        databaseConnection = new DatabaseConnection() {
            @Override
            public boolean checkUserPass(String name, String password) {
                return true;
            }
        };
        User u = new User(databaseConnection);

        String user = "Valaki";
        String password = "123";

        Integer sum = u.sumData(user, password, 5, 4);
        Integer expected = 9;

        assertEquals(sum, expected);
    }

    @Test
    public void testSumWithMockAndCorrectData(){

        u = new User(databaseConnection);
        String user = "Valaki";
        String password = "123";

        when(databaseConnection.checkUserPass(user, password)).thenReturn(true);

        Integer sum = u.sumData(user, password, 5, 4);
        Integer expected = 9;

        assertEquals(sum, expected);
    }

    @Test
    public void testSumWithMockAndNotData(){
        u = new User(databaseConnection);
        String user = "Valaki";
        String password = "123";

        //TODO MOCK
        when(databaseConnection.checkUserPass(user, password)).thenReturn(false);

        Integer sum = u.sumData(user, password, 5, 4);
        Integer expected = null;

        assertEquals(sum, expected);
    }
}