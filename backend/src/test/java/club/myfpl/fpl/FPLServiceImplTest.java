package club.myfpl.fpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Saket
 * @since 23/04/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FPLServiceImplTest {

    @Autowired
    private FPLService fplService;

    @Test
    public void grabAndSaveBootstrapStaticData() throws Exception {
        fplService.grabAndSaveBootstrapStaticData();
    }

}