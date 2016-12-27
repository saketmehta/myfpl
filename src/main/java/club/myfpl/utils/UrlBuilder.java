package club.myfpl.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * User: Saket
 * Date: 25/12/16
 * Time: 9:18 PM
 */
public class UrlBuilder {

    private StringBuilder url;
    private StringBuilder query;

    private UrlBuilder(String base) {
        this.url = new StringBuilder(base);
        this.query = new StringBuilder();
    }

    public static UrlBuilder withBase(String base) {
        return new UrlBuilder(base);
    }

    public UrlBuilder queryParam(String name, String value) {
        if (query.length() > 0) {
            query.append("&");
        }
        query.append(name).append("=").append(value);
        return this;
    }

    public String build() {
        StringBuilder result = new StringBuilder(url.toString());
        if (StringUtils.isNotEmpty(query)) {
            result.append(query);
        }
        return result.toString();
    }
}
