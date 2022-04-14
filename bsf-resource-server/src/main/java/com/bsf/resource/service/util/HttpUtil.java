package com.bsf.resource.service.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

@Slf4j
public class HttpUtil {
   // private final static String USER_AGENT = "Mozilla/5.0";
    public static JsonObject connectionPostParameters = null;

    /**
     * @param data
     * @return String[]
     * @throws Exception
     */
    public static String[] sendGet(JsonObject data) throws Exception {
        String url = data.get("url").getAsString();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        con = setConnectionProperties(con, data);

        int responseCode = con.getResponseCode();
        log.info("Sending 'GET' request to URL : " + url);
        log.info("Response Code : " + responseCode);

        log.info("Sending 'GET' request to URL : {}", url);
        log.info("Response Code : ", responseCode);

        InputStream stream = responseCode == 200 ? con.getInputStream() : con.getErrorStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String[] resp = {responseCode + "", response.toString()};

        log.info(response.toString());
        log.info("response from service {}   {}", resp[0], resp[1]);
        return resp;
    }

    /**
     * @param con
     * @param data
     * @return HttpURLConnection
     */
    private static HttpURLConnection setConnectionProperties(HttpURLConnection con, JsonObject data) {

        if (data != null) {
            for (Map.Entry<String, JsonElement> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().getAsString();
                if (!key.equalsIgnoreCase("url")) {
                    con.setRequestProperty(key, value);

                    if (key.equalsIgnoreCase("timeout")) con.setConnectTimeout(Integer.parseInt(value));
                    if (key.equalsIgnoreCase("readTimeout")) con.setReadTimeout(Integer.parseInt(value));
                    if (key.equalsIgnoreCase("doOutput")) con.setDoOutput(entry.getValue().getAsBoolean());
                }
            }
        }
        return con;
    }

    /**
     * @param endpoint
     * @param jsonString
     * @return String[]
     * @throws Exception
     */
    public static String[] sendPost(String endpoint, String jsonString) throws Exception {
        log.info("\nSending 'POST' request to URL : {}", endpoint);
        log.info("Post parameters : {}", jsonString);

        // Create a context that doesn't check certificates.
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager[] trustManager = getTrustManager();
        sslContext.init(null, trustManager, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        URL url = new URL(endpoint);
        //HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con = setConnectionProperties(con, connectionPostParameters);
        con.setDoOutput(true);


        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.flush();
        }

        int responseCode = con.getResponseCode();
        log.info("Response Code : {}" + responseCode);

        InputStream stream = responseCode == 200 ? con.getInputStream() : con.getErrorStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String[] resp = {responseCode + "", response.toString()};
        return resp;
    }

    /**
     * @return TrustManager[]
     */
    private static TrustManager[] getTrustManager() {
        TrustManager[] certs = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String t) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String t) {
            }
        }
        };
        return certs;
    }
}