package emberek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListTest {

    EmberLista emberLista;

    @Before
    public void setUp(){
        emberLista = new EmberLista();
        emberLista.add("János");
        emberLista.add("Béla");
        emberLista.add("József");
        emberLista.add("Judit");
        emberLista.add("Judit");
    }

    @Test
    public void checkSize(){
        int actualSize= emberLista.getSize();
        int expectedSize = 4;

        assertEquals(expectedSize,actualSize);
    }

    @Test
    public void checkEmptiness(){
        boolean actualEmpty= emberLista.isEmpty();
        assertFalse(actualEmpty);
    }

    @Test
    public void newEmberBySize(){
        int actualSize= emberLista.getSize();
        emberLista.add("Pista");
        int newSize = emberLista.getSize();

        assertEquals(actualSize+1, newSize);
    }

    @Test
    public void newEmberByName(){
        emberLista.add("Pista");
        assertTrue(emberLista.isInEmber("Pista"));
    }

    @Test
    public  void removeAll(){
        emberLista.removeAll();

        int expected = 0;
        int actualSize = emberLista.getSize();

        assertEquals(expected,actualSize);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteNonExistingEmber(){
        String nonExistingEmberName = "Alfréd";
        emberLista.remove(nonExistingEmberName);
    }

    @Test
    public void deleteExistingEmber(){
        String existingEmberName = "József";
        emberLista.remove(existingEmberName);
    }


    @After
    public  void removeAllAfterTests(){
        emberLista.removeAll();
    }


}