package club.myfpl.fpl;

import club.myfpl.fpl.responses.BootstrapDynamicDataResponse;
import club.myfpl.fpl.responses.BootstrapStaticDataResponse;
import club.myfpl.fpl.responses.EventDataResponse;
import club.myfpl.utils.JsonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * User: Saket
 * Date: 11/01/17
 * Time: 9:28 PM
 */
public class FPLAPIClientImpl implements FPLAPIClient {

    @Override
    public BootstrapDynamicDataResponse fetchBootstrapDynamicData() {
        return doAPICall(FPLConstants.BOOTSTRAP_DYNAMIC_ENDPOINT, BootstrapDynamicDataResponse.class);
    }

    @Override
    public BootstrapStaticDataResponse fetchBootstrapStaticData() {
        return doAPICall(FPLConstants.BOOTSTRAP_STATIC_ENDPOINT, BootstrapStaticDataResponse.class);
    }

    @Override
    public EventDataResponse fetchEventData(int event) {
        return doAPICall(String.format(FPLConstants.EVENT_ENDPOINT, event), EventDataResponse.class);
    }

    private <T> T doAPICall(String relativePath, Class<T> responseClass) {
        String absoluteURL = FPLConstants.BASE_URL + relativePath;
        T response;
        try {
            URL url = new URL(absoluteURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return JsonUtils.fromJson(buffer.toString(), responseClass);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while request at URL: " + absoluteURL, e);
        }
    }

}
