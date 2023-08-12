package codesample;

import burp.api.montoya.http.handler.HttpHandler;
import burp.api.montoya.http.handler.RequestToBeSentAction;
import burp.api.montoya.http.handler.ResponseReceivedAction;
import burp.api.montoya.http.handler.HttpRequestToBeSent;
import burp.api.montoya.http.handler.HttpResponseReceived;
import burp.api.montoya.core.Annotations;

public class CustomHttpHandler implements HttpHandler {
    /**
     * Invoked by Burp when an HTTP request is about to be sent.
     *
     * @param requestToBeSent information about the HTTP request that is going to be sent.
     *
     * @return An instance of {@link RequestToBeSentAction}.
     */
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        try {
            if (RequestPatcher.shouldPatch(requestToBeSent)) {
                return RequestToBeSentAction.continueWith(RequestPatcher.patch(requestToBeSent), Annotations.annotations("modifed by " + App.name));
            }
        } catch (Exception e) {
            App.logging.logToError(e.toString());
        }
        return RequestToBeSentAction.continueWith(requestToBeSent);
    }

    /**
     * Invoked by Burp when an HTTP response has been received.
     *
     * @param responseReceived information about HTTP response that was received.
     *
     * @return An instance of {@link ResponseReceivedAction}.
     */
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived responseReceived) {
        try {

            if (RequestPatcher.shouldPatch(responseReceived)) {
                return ResponseReceivedAction.continueWith(RequestPatcher.patch(responseReceived), Annotations.annotations("modifed by " + App.name));
            }
        } catch (Exception e) {
            App.logging.logToError(e.toString());
        }
        return ResponseReceivedAction.continueWith(responseReceived);
    }
}
