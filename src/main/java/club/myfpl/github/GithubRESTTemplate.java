package club.myfpl.github;

import club.myfpl.utils.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 11:11 PM
 */
public class GithubRESTTemplate {
    private String accessToken;

    public GithubRESTTemplate(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public GithubUser getUserProfile() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.github.com/user");
        httpGet.addHeader(HttpHeaders.AUTHORIZATION, "token " + accessToken);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        InputStream content = httpResponse.getEntity().getContent();
        return JsonUtils.fromJson(IOUtils.toString(content, Charset.defaultCharset()), GithubUser.class);
    }
}
