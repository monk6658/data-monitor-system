package com.monitor.datamonitorsystem.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket服务类
 * @author zxl
 * @time 2020/4/24 10:10
 */
@Component
@ServerEndpoint(value = "/websocket")
public class WebSocketServer {

    /** 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。 */
    private static int onlineCount = 0;

    /** concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。 */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    /** 与某个客户端的连接会话，需要通过它来给客户端发送数据 */
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    public WebSocketServer() {
        System.out.println("初始化！");
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {

            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 向页面发送信息
     * @author zxl
     * @time 2020/4/26 11:20
     */
    public void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }


    /**
     * 群发消息
     * */
    public static void sendInfo(String message) {
        System.out.println(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}


