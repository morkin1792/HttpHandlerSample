package handlersample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;

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

    public static String executeCommand(String command, boolean throwCommandError) throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder builder;

        if (os.contains("win")) {
            builder = new ProcessBuilder("cmd.exe", "/c", command);
        } else {
            builder = new ProcessBuilder("bash", "-c", command);
        }

        builder.redirectErrorStream(true);
        Process process = builder.start();

        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            if (throwCommandError) {
                throw new RuntimeException("Command exited with code " + exitCode + "\nOutput:\n" + output);
            } else {
                return "Command exited with code " + exitCode + "\nOutput:\n" + output;
            }
        }
        return output.toString().trim();

    }
}
