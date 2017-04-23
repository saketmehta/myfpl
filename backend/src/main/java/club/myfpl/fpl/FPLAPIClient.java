package club.myfpl.fpl;

import club.myfpl.fpl.responses.BootstrapDynamicDataResponse;
import club.myfpl.fpl.responses.BootstrapStaticDataResponse;
import club.myfpl.fpl.responses.EventDataResponse;

/**
 * User: Saket
 * Date: 11/01/17
 * Time: 9:04 PM
 */
public interface FPLAPIClient {

    BootstrapDynamicDataResponse fetchBootstrapDynamicData();

    BootstrapStaticDataResponse fetchBootstrapStaticData();

    EventDataResponse fetchEventData(int event);

}
