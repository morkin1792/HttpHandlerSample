package handlersample;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Scanner;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;


public class App implements BurpExtension {
    public static final String name = "HttpHandlerSample";
    public static Logging logging;
    
    public void initialize(MontoyaApi api) {
        api.extension().setName(name);
        logging = api.logging();

        var proxyHandler = new CustomProxyHandler();
        var nonProxyHandler = new CustomNonProxyHandler();

        api.http().registerHttpHandler(nonProxyHandler);
        api.proxy().registerRequestHandler(proxyHandler);
        api.proxy().registerResponseHandler(proxyHandler);

    }

    public static String findString(String data, String initialPattern, String finalPattern) {
        data = data.substring(data.indexOf(initialPattern) + initialPattern.length());
        data = data.substring(0, data.indexOf(finalPattern));
        return data;
    }

    public static String getTimeNow() {
        return ZonedDateTime.now().toString().split("[.]")[0];
    }

    public static String executeCommand(String command) throws Exception {
        String result = null;
        logging.logToOutput("$ " + command);
        Process process = Runtime.getRuntime().exec(new String[] {"/bin/sh","-c", command});
        InputStream inputStream = process.getInputStream();
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        result = scanner.hasNext() ? scanner.next().trim() : null;
        return result;
    }
}
