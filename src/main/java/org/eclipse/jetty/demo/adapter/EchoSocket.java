package org.eclipse.jetty.demo.adapter;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoSocket extends WebSocketAdapter
{
    private static final Logger LOG = LoggerFactory.getLogger(EchoSocket.class);

    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode, reason);
        LOG.info("WebSocket Close: {} - {}", statusCode, reason);
    }

    public void onWebSocketConnect(Session session)
    {
        super.onWebSocketConnect(session);
        LOG.info("WebSocket Connect: {}", session);
        getRemote().sendString("You are now connected to " + this.getClass().getName(), WriteCallback.NOOP);
    }

    public void onWebSocketError(Throwable cause)
    {
        LOG.warn("WebSocket Error", cause);
    }

    public void onWebSocketText(String message)
    {
        if (isConnected())
        {
            LOG.info("Echoing back text message [{}]", message);
            getRemote().sendString(message, WriteCallback.NOOP);
        }
    }

    @Override
    public void onWebSocketBinary(byte[] arg0, int arg1, int arg2)
    {
        /* ignore */
    }
}
