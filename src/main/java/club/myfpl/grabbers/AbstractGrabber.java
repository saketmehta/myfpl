package club.myfpl.grabbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * User: Saket
 * Date: 24/11/16
 * Time: 8:47 PM
 */
abstract class AbstractGrabber implements Grabber {

    static final String BASE_FPL_ENDPOINT = "https://fantasy.premierleague.com/drf";

    String readUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } catch (IOException ignored) {
        }
        return "";
    }
}
