package handlersample;

import burp.api.montoya.proxy.http.InterceptedRequest;
import burp.api.montoya.proxy.http.InterceptedResponse;
import burp.api.montoya.proxy.http.ProxyRequestHandler;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction;
import burp.api.montoya.proxy.http.ProxyResponseHandler;
import burp.api.montoya.proxy.http.ProxyResponseReceivedAction;
import burp.api.montoya.proxy.http.ProxyResponseToBeSentAction;
import handlersample.RequestPatcher;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.ToolType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;


public class CustomProxyHandler implements ProxyRequestHandler, ProxyResponseHandler {
    
    public ProxyRequestReceivedAction handleRequestReceived(InterceptedRequest requestReceived){
        return ProxyRequestReceivedAction.continueWith(requestReceived);
    }
    
    public ProxyRequestToBeSentAction handleRequestToBeSent(InterceptedRequest requestToBeSent) {
        try {
            if (RequestPatcher.shouldPatchRequest(requestToBeSent, ToolType.PROXY)) {
                boolean[] modified = new boolean[]{ false };
                HttpRequest request = RequestPatcher.patchRequest(requestToBeSent, modified);
                if (modified[0]) {
                    return ProxyRequestToBeSentAction.continueWith(request, Annotations.annotations("modified by " + App.name));
                }
            }
        } catch (Exception e) {
            App.logging.logToError(e.toString());
        }
        return ProxyRequestToBeSentAction.continueWith(requestToBeSent);
    }

    public ProxyResponseReceivedAction handleResponseReceived(InterceptedResponse responseReceived) {
        return ProxyResponseReceivedAction.continueWith(responseReceived);
    }

    public ProxyResponseToBeSentAction handleResponseToBeSent(InterceptedResponse responseToBeSent) {
        try {
            if (RequestPatcher.shouldPatchResponse(responseToBeSent, responseToBeSent.initiatingRequest(), ToolType.PROXY)) {
                boolean[] modified = new boolean[]{ false };
                HttpResponse response = RequestPatcher.patchResponse(responseToBeSent, responseToBeSent.initiatingRequest(), modified);
                if (modified[0]) {
                    return ProxyResponseToBeSentAction.continueWith(response, Annotations.annotations("modified by " + App.name));
                }
            }
        } catch (Exception e) {
            App.logging.logToError(e.toString());
        }
        return ProxyResponseToBeSentAction.continueWith(responseToBeSent);
    }
    
}
