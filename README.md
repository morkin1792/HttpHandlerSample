# BAppCodeSample

This is an incomplete extension for the [Burp Suite](https://portswigger.net/burp). The goal here is to provide an easy starting point for programatically handling request/responses from any Burp tool (proxy, repeater, ...).
To complete this extension, the class [RequestPatcher](app/src/main/java/codesample/RequestPatcher.java) must be implemented with the following methods:

```java
public class RequestPatcher {
    public static HttpRequest patch(HttpRequestToBeSent request);
    public static boolean shouldPatch(HttpRequestToBeSent request);
    public static HttpResponse patch(HttpResponseReceived response);
    public static boolean shouldPatch(HttpResponseReceived response);
}
```

Then the extension can be built using the command:

```bash
./gradlew build
```

Finally, load the JAR file (./app/build/libs/codesample.jar) into the Burp Suite by navigating to Extensions > Installed.