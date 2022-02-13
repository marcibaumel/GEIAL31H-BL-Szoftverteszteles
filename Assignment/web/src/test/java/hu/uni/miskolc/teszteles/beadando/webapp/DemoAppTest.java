package hu.uni.miskolc.teszteles.beadando.webapp;

import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoAppTest {

    @Test
    public void main() {
        DemoApp.main(new String[] {});
    }

    @Test
    public void contextLoads() {
    }
}