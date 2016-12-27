package club.myfpl.security;

import club.myfpl.beans.User;
import club.myfpl.github.*;
import club.myfpl.services.UserService;
import club.myfpl.utils.JsonUtils;
import club.myfpl.utils.UrlBuilder;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 9:05 PM
 */
// TODO: 27/12/16
@Service
@RestController
@RequestMapping("oauth")
public class OauthController {
    private static final String GITHUB_CLIENT_ID = "09a675a38c4c7e00e68a";
    private static final String GITHUB_CLIENT_SECRET = "de9beebf6f59e6ddb959cdcf6dd887f123e871f2";

    private final GithubOauthService githubOauthService;
    private final UserService userService;

    @Autowired
    public OauthController(GithubOauthService githubOauthService, UserService userService) {
        this.githubOauthService = githubOauthService;
        this.userService = userService;
    }

    @RequestMapping("login/github")
    public void loginWithGithub(HttpServletResponse response) throws IOException {
        String location = UrlBuilder.withBase("https://github.com/login/oauth/authorize")
                .queryParam("client_id", GITHUB_CLIENT_ID)
                .queryParam("redirect_uri", "http://localhost:8000/oauth/callback/github")
                .queryParam("state", "xyz")
                .build();
        response.sendRedirect(location);
    }

    @RequestMapping(value = "callback/github")
    public void githubCallbackHandler(@RequestParam String code, @RequestParam String state, HttpServletRequest request) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String location = UrlBuilder.withBase("https://github.com/login/oauth/access_token").build();

        HttpPost httpPost = new HttpPost(location);
        List<NameValuePair> parameters = Lists.newArrayList();
        parameters.add(new BasicNameValuePair("client_id", GITHUB_CLIENT_ID));
        parameters.add(new BasicNameValuePair("client_secret", GITHUB_CLIENT_SECRET));
        parameters.add(new BasicNameValuePair("code", code));
        parameters.add(new BasicNameValuePair("redirect_uri", "http://localhost:8000/oauth/callback/github"));
        parameters.add(new BasicNameValuePair("state", state));
        httpPost.setEntity(new UrlEncodedFormEntity(parameters));
        httpPost.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        InputStream inputStream = httpResponse.getEntity().getContent();
        GithubOauthResponse githubOauthResponse = JsonUtils.fromJson(IOUtils.toString(inputStream, Charset.defaultCharset()), GithubOauthResponse.class);
        GithubRESTTemplate githubRESTTemplate = new GithubRESTTemplate(githubOauthResponse.getAccessToken());
        GithubUser userProfile = githubRESTTemplate.getUserProfile();
        User user = userService.fetchUserByEmail(userProfile.getEmail());
        GithubOauthToken token = new GithubOauthToken(user.getUserId(), githubOauthResponse.getAccessToken());
        token.setDetails(user);
        githubOauthService.saveToken(token);

        SecurityContextHolder.getContext().setAuthentication(token);
    }

}
