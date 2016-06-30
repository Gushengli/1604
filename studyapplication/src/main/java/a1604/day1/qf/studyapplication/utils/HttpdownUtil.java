package a1604.day1.qf.studyapplication.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpdownUtil {

    private static URL url = null;
    private static HttpURLConnection openConnection = null;

    public static byte[] getdown(String strurl) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            url = new URL(strurl);
            try {
                openConnection = (HttpURLConnection) url.openConnection();
                openConnection.setRequestMethod("GET");
                if (openConnection.getResponseCode() == 200) {
                    InputStream isput = openConnection.getInputStream();
                    byte[] array = new byte[1024];
                    int len = 0;
                    while ((len = isput.read(array)) != -1) {
                        bos.write(array, 0, len);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
