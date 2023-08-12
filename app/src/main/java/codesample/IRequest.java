package codesample;
import burp.api.montoya.core.ToolSource;
import burp.api.montoya.http.handler.HttpRequestToBeSent;
import burp.api.montoya.http.handler.HttpResponseReceived;
import burp.api.montoya.http.message.HttpHeader;
import java.util.List;


interface IRequest {
    ToolSource toolSource();
    List<HttpHeader> headers();

    public static IRequest fromRequest(HttpRequestToBeSent request) {
        return new IRequest() {
            public List<HttpHeader> headers() {return request.headers();}
            public ToolSource toolSource() {return request.toolSource();}
        };
    }

    public static IRequest fromResponse(HttpResponseReceived response) {
        return new IRequest() {
            public List<HttpHeader> headers() {return response.headers();}
            public ToolSource toolSource() {return response.toolSource();}
        };
    }
}