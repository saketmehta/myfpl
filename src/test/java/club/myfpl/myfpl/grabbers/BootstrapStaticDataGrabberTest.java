package club.myfpl.myfpl.grabbers;

import club.myfpl.grabbers.BootstrapStaticDataGrabber;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:19 AM
 */
@Service
public class BootstrapStaticDataGrabberTest {

    @Autowired
    private BootstrapStaticDataGrabber bootstrapStaticDataGrabber;

    @Test
    public void testGrab() throws Exception {
        bootstrapStaticDataGrabber.grabAndSaveData();
    }
}