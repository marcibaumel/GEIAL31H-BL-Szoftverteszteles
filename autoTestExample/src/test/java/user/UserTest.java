package user;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {



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

        //when(databaseConnection.checkUserPass(user, password)).thenReturn(true);

        Integer sum = u.sumData(user, password, 5, 4);
        Integer expected = 9;

        assertEquals(sum, expected);

    }
    @Mock DatabaseConnection databaseConnection;

    @Test
    public void testSumWithMock(){

        User u = new User(databaseConnection);
        String user = "Valaki";
        String password = "123";

        //TODO MOCK
        when(databaseConnection.checkUserPass(user, password)).thenReturn(true);

        Integer sum = u.sumData(user, password, 5, 4);
        Integer expected = 9;

        assertEquals(sum, expected);

    }
}