package club.myfpl.fpl;

/**
 * User: Saket
 * Date: 11/01/17
 * Time: 9:48 PM
 */
public interface FPLService {
    void grabAndSaveBootstrapDynamicData();

    void grabAndSaveBootstrapStaticData();

    void grabAndSaveEventData(int eventId);
}
