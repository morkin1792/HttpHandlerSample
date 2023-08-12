/*
 * This class is used to handle HTTP requests programatically
 * in Burp Suite, it is part of the following extension: 
 * https://github.com/morkin1792/BAppCodeSample
 */
package codesample;

import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import burp.api.montoya.http.handler.HttpRequestToBeSent;
import burp.api.montoya.http.handler.HttpResponseReceived;
// import burp.api.montoya.http.message.params.HttpParameter;

public class RequestPatcher {
   
    /**
     * Invoked before the HTTP request be sent.
     *
     * @param request the original HTTP request.
     *
     * @return the new HTTP request to be sent.
     */
    public static HttpRequest patch(HttpRequestToBeSent request) throws Exception {
        // for (HttpParameter param : request.parameters()) {
        //     if (param.name().equals("username")) {
        //         String userId = App.getHeader(IRequest.fromRequest(request), "User-ID");
        //         App.logging.logToOutput("UserID: " + userId);
        //         String[] paramArray = param.value().split("[/]");
        //         String newUser = App.executeCommand("echo user2");
        //         paramArray[1] = newUser;
        //         String newValue = String.join(".", paramArray);
        //         // tip: currently HttpRequest.withParameter() may not work as well
        //         return request.withBody("{\"username\":\"" + newValue + "\",\"password\":\"123\"}");
        //     }
        // }
        // return request;
        throw new Exception("method not implemented yet");
    }

    /**
     * Invoked when the HTTP response has been received.
     *
     * @param response the original HTTP response.
     *
     * @return the new HTTP response.
     */
    public static HttpResponse patch(HttpResponseReceived response) throws Exception {
        throw new Exception("method not implemented yet");
    }

    /**
     * Invoked before the method patch to check if the request is in scope.
     * 
     * @param request the original HTTP request.
     * 
     * @return A boolean that indicates whether the request is in scope.
     */
    public static boolean shouldPatch(HttpRequestToBeSent request) throws Exception {
        throw new Exception("method not implemented yet");
    }


    /**
     * Invoked before the method patch to check if the response is in scope.
     * 
     * @param response the original HTTP response.
     * 
     * @return A boolean that indicates whether the response is in scope.
     */
    public static boolean shouldPatch(HttpResponseReceived response) throws Exception {
        // return App.inProxy(IRequest.fromResponse(response)) 
        //     && response.initiatingRequest().method().equals("POST") 
        //     && response.initiatingRequest().url().contains("api.example.com/login");
        throw new Exception("method not implemented yet");

    }
    
}
