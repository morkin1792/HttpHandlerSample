package handlersample;

import burp.api.montoya.http.handler.HttpHandler;
import burp.api.montoya.http.handler.RequestToBeSentAction;
import burp.api.montoya.http.handler.ResponseReceivedAction;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.http.handler.HttpRequestToBeSent;
import burp.api.montoya.http.handler.HttpResponseReceived;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ToolType;

public class CustomNonProxyHandler implements HttpHandler {

    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        // Only handle requests not from Proxy (handled in CustomProxyHandler)    
        if (requestToBeSent.toolSource().toolType() != ToolType.PROXY) {
            try {
                if (RequestPatcher.shouldPatchRequest(requestToBeSent, requestToBeSent.toolSource().toolType())) {
                    boolean[] modified = new boolean[]{ false };
                    HttpRequest request = RequestPatcher.patchRequest(requestToBeSent, modified);
                    if (modified[0]) {
                        return RequestToBeSentAction.continueWith(request, Annotations.annotations("modified by " + App.name));
                    }
                }
            } catch (Exception e) {
                App.logging.logToError(e.toString());
            }
        }
        return RequestToBeSentAction.continueWith(requestToBeSent);
    }

    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived responseReceived) {
        // Only handle responses not from Proxy (handled in CustomProxyHandler)
        if (responseReceived.toolSource().toolType() != ToolType.PROXY) {
            try {
                if (RequestPatcher.shouldPatchResponse(responseReceived, responseReceived.initiatingRequest(), responseReceived.toolSource().toolType())) {
                    boolean[] modified = new boolean[]{ false };
                    HttpResponse response = RequestPatcher.patchResponse(responseReceived, responseReceived.initiatingRequest(), modified);
                    if (modified[0]) {
                        return ResponseReceivedAction.continueWith(response, Annotations.annotations("modified by " + App.name));
                    }
                }
            } catch (Exception e) {
                App.logging.logToError(e.toString());
            }
        }
        return ResponseReceivedAction.continueWith(responseReceived);
    }
}
