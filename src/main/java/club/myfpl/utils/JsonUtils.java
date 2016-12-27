package club.myfpl.utils;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * User: Saket
 * Date: 24/11/16
 * Time: 9:02 PM
 */
public class JsonUtils {
    private static final Gson GSON = new Gson();

    public static <T> T fromJson(String data, Class<T> clazz) {
        return GSON.fromJson(data, clazz);
    }

    public static <T> T fromJson(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> T fromJson(InputStream inputStream, Class<T> clazz) {
        Reader reader = new InputStreamReader(inputStream);
        return GSON.fromJson(reader, clazz);
    }
}
