/*
 * This class is used to handle HTTP requests and responses programatically via Burp Suite.
 * It is part of the following extension: https://github.com/morkin1792/HttpHandlerSample
 */
package handlersample;

import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.core.ToolType;
import burp.api.montoya.http.message.params.HttpParameter;
import burp.api.montoya.http.message.params.HttpParameterType;


public class RequestPatcher {
    
    /**
     * This method is invoked for all HTTP request.
     *
     * <p>This method allows filtering which requests should be processed by the patchRequest method.</p>
     *
     * @param request The original HTTP request only to be read (to modify use patchRequest).
     * @param toolType The tool from which the request originated (ToolType.PROXY, ToolType.REPEATER, etc.).
     * 
     * @return {@code true} if the request should be processed by the patchRequest method, {@code false} otherwise.
     */
    public static boolean shouldPatchRequest(HttpRequest request, ToolType toolType) {
        // return toolType == ToolType.PROXY 
        //     && request.method().equals("GET") 
        //     && request.url().contains("target.com/api/v2/token");
        return true;
    }
    
    /**
     * Invoked for each HTTP request where shouldPatchRequest returns true.
     *
     * <p>This method allows reading and/or modifying the request before it is sent to the server.</p>
     *
     * @param request The original HTTP request to be read/patched.
     * @param refModifed A boolean array used as a reference to indicate if the request was modified.
     *                   The first element of the array MUST be set to {@code true} if the request was modified.
     *                   Otherwise, the modification will be ignored.
     * 
     * @return The modified HTTP request.
     * @throws Exception A generic exception.
     */
    public static HttpRequest patchRequest(HttpRequest request, boolean[] refModifed) throws Exception {
        // String userAgent = request.header("User-Agent").value();
        // App.logging.logToOutput("[GETTING USER-AGENT]");
        // App.logging.logToOutput("User-Agent: " + userAgent);
        // App.executeCommand("echo '" + App.getTimeNow() + ": " + request.url().replace("'", "%27") + "' >> /tmp/urls.txt");
        // request = request.withUpdatedHeader("User-Agent", "curl/9");
        // refModifed[0] = true;

        // for (HttpParameter param : request.parameters()) {
        //     if (param.name().equals("username")) {
        //         request = request.withUpdatedParameters(
        //             HttpParameter.parameter("username", "john", HttpParameterType.JSON)
        //         );
        //     }
        // }
        return request;
    }

    /**
     * This method is invoked for all HTTP response.
     *
     * <p>This method allows filtering which responses should be processed by the patchResponse method.</p>
     *
     * @param response The original HTTP response only to be read (to modify use patchResponse).
     * @param request The HTTP request linked to the received response only to be read (to modify use patchRequest).
     * @param toolType The tool from which the response originated (ToolType.PROXY, ToolType.REPEATER, etc.).
     * 
     * @return {@code true} if the response should be processed by the patchResponse method, {@code false} otherwise.
     */
    public static boolean shouldPatchResponse(HttpResponse response, HttpRequest request, ToolType toolType) {
        // return toolType == ToolType.PROXY
        //     && request.method().equals("GET") 
        //     && request.url().contains("target.com/api/v2/token");
        return true;
    }

    /**
     * Invoked for each HTTP response where shouldPatchResponse returns true.
     *
     * <p>This method allows reading and/or modifying the response before it is sent to the client.</p>
     *
     * @param response The original HTTP response to be read/patched.
     * @param request The HTTP request linked to the received response only to be read (to modify use patchRequest).
     * @param refModifed A boolean array used as a reference to indicate if the response was modified.
     *                   The first element of the array MUST be set to {@code true} if the response was modified.
     *                   Otherwise, the modification will be ignored.
     * 
     * @return The modified HTTP response.
     * @throws Exception A generic exception.
     */
    public static HttpResponse patchResponse(HttpResponse response, HttpRequest request, boolean[] refModifed) throws Exception {
        
        // String server = response.header("Server").value();
        // App.logging.logToOutput("[GETTING SERVER FROM " + request.url() + " ]");
        // App.logging.logToOutput("Server: " + server);

        return response;
    }

}
