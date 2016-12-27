package club.myfpl.myfpl.grabbers;

import club.myfpl.grabbers.EventGrabber;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 4:05 AM
 */
@Service
public class EventGrabberTest {

    @Autowired
    private EventGrabber eventGrabber;

    @Test
    public void testGrab() {
        eventGrabber.grabAndSaveData();
    }

    @Test
    public void testGrabAll() {
        eventGrabber.grabAll();
    }

    @Test
    public void testGrabEvent() {
        eventGrabber.grabEvent(2L);
    }
}