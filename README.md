Embedded Jetty Examples: Echo Servers
=====================================

Included in this git repository are a collection of 5 different ways to
create a websocket echo server in Embedded Jetty.

See src/main/java for the code examples.

Jetty WebSocket Native API Examples

 * org.eclipse.jetty.demo.listener - create a WebSocket using a WebSocketListener
 * org.eclipse.jetty.demo.adapter - create a WebSocket using a WebSocketAdapter
 * org.eclipse.jetty.demo.annotated - create a WebSocket using @WebSocket annotation

JSR-356 (javax.websocket) API Examples

 * org.eclipse.jetty.demo.jsr.annotated - create a WebSocket using @ServerEndpoint annotation
 * org.eclipse.jetty.demo.jsr.endpoint - create a WebSocket using a javax.websocket.EndPoint

