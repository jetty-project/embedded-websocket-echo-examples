package org.eclipse.jetty.demo.annotated;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebSocket
public class EchoSocket
{
    private static final Logger LOG = LoggerFactory.getLogger(EchoSocket.class);
    private Session session;
    private RemoteEndpoint remote;

    @OnWebSocketClose
    public void onWebSocketClose(int statusCode, String reason)
    {
        this.session = null;
        this.remote = null;
        LOG.info("WebSocket Close: {} - {}",statusCode,reason);
    }

    @OnWebSocketConnect
    public void onWebSocketConnect(Session session)
    {
        this.session = session;
        this.remote = this.session.getRemote();
        LOG.info("WebSocket Connect: {}", session);
        this.remote.sendString("You are now connected to " + this.getClass().getName(), WriteCallback.NOOP);
    }

    @OnWebSocketError
    public void onWebSocketError(Throwable cause)
    {
        LOG.warn("WebSocket Error",cause);
    }

    @OnWebSocketMessage
    public void onWebSocketText(String message)
    {
        if (this.session != null && this.session.isOpen() && this.remote != null)
        {
            LOG.info("Echoing back text message [{}]", message);
            this.remote.sendString(message, WriteCallback.NOOP);
        }
    }
}
