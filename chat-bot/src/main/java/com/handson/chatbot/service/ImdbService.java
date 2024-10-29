package com.handson.chatbot.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImdbService {

    public static final Pattern PRODUCT_PATTERN = Pattern.compile("alt=\"([^\"]*Margaret Qualley[^\"]*)\"");

    public String searchProducts(String keyword) {
        return "Searched for: " + keyword;
    }

    public String parseProductHtml(String html) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = PRODUCT_PATTERN.matcher(html);
        while (matcher.find()) {
            result.append(matcher.group(1)).append("<br>\n"); // Captures the alt text
        }
        return result.toString();
    }

    public String getProductHtml(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.imdb.com/scary-good/21st-century-scream-queens/rg2753600256/mediaviewer/rm3587663617/")
                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .addHeader("accept-language", "en-US,en;q=0.9,he;q=0.8")
                .addHeader("cookie", "session-id-time=2082787201l; ad-oo=0; ci=e30; session-id=133-5987756-7436247; ubid-main=131-1408892-4770243; csm-hit=tb:DQ5GP1VKMWY3C0NPV41K+s-E617NXX1XXCD2VK6Q5E0|1730103484802&t:1730103484802&adb:adblk_no; session-id=133-5987756-7436247; session-id-time=2082787201l; session-token=sGhXIC4MzWGr+FU8+K6P/aBYA5neq85zTM3HTcNNdkpw81H4Bp/toQ7UlvwmlG94hceezY6GrmLSc5mxx8xuHR0bZH7yvyrwevqW2h5Ytn4MJwL2QG+GpPh6+Wb2OshPes/43u6vnVbfJpexVsLGmMDqYEfQm9+PBQ7REF3+yb7sXb5RocbyQd3cnZ7UvotpX6MkAjEb/w+OAkCMV+QsJreSkb6GytQZ3Fa/34ye/d4js5vbXVLM0jXIWhdNJ9JHk9Tw4p+IYr/hcDtX9ayh5isXmbFbFmhg0IwLiogkfkm0iSv7Cr7vOXR7YkVU/vLaP49eK+GVtrGFVftjUzoj22rgTohAN4hR")
                .addHeader("priority", "u=0, i")
                .addHeader("referer", "https://www.imdb.com/")
                .addHeader("sec-ch-ua", "\"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "document")
                .addHeader("sec-fetch-mode", "navigate")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-user", "?1")
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
