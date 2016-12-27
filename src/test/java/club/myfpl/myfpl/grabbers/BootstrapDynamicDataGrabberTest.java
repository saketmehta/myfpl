package club.myfpl.myfpl.grabbers;

import club.myfpl.grabbers.BootstrapDynamicDataGrabber;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 12:47 AM
 */
@Service
public class BootstrapDynamicDataGrabberTest {

    @Autowired
    private BootstrapDynamicDataGrabber bootstrapDynamicDataGrabber;

    @Test
    public void testGrab() {
        bootstrapDynamicDataGrabber.grabAndSaveData();
    }
}