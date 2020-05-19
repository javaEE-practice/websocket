package com.wch.websocket.server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();

    /**
     * 	连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        SessionSet.add(session);
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        System.out.println("有连接加入，当前连接数为："+cnt);
        SendMessage(session, "连接成功");
    }
    /**
     * 	连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        int cnt = OnlineCount.decrementAndGet();
        System.out.println("有连接关闭，当前连接数为："+cnt);
    }
    /**
     * 	收到客户端消息后调用的方法
     * @param message
     * @throws InterruptedException 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws InterruptedException {
        System.out.println("来自客户端的消息："+message);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(!message.contains("13-14")) {
        	Thread.sleep(5000);
        	Date date = new Date();
        	SendMessage(session, "服务端返回消息：" + format.format(date));
        }
    }
    /**
     *	 出现错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("error："+error);
    }
    /**
     * 	发送消息，实践表明，每次浏览器刷新，session会发生变化。
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Session ID："+session.getId()+"===="+message);
        }
    }
    /**
     * 	群发消息
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) throws IOException {
        for (Session session : SessionSet) {
            if(session.isOpen()){
                SendMessage(session, message);
            }
        }
    }
    /**
     *	 指定Session发送消息
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void SendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : SessionSet) {
            if(s.getId().equals(sessionId)){
                session = s;
                break;
            }
        }
        if(session!=null){
            SendMessage(session, message);
        }else{
            System.out.println("没有找到你指定ID的会话：" + sessionId);
        }
    }

}
