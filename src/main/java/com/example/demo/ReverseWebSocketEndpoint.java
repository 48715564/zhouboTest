package com.example.demo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/auctionWebSocket/{goods_id}")
public class ReverseWebSocketEndpoint {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private static ConcurrentHashMap<String, CopyOnWriteArraySet<ReverseWebSocketEndpoint>> map = new ConcurrentHashMap<>();

    private final Log logger = LogFactory.getLog(getClass());

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        String goods_id = session.getPathParameters().get("goods_id");
        CopyOnWriteArraySet<ReverseWebSocketEndpoint> webSocketSets = map.get(goods_id);
        if (webSocketSets == null) {
            webSocketSets = new CopyOnWriteArraySet<>();
        }
        webSocketSets.add(this);     //加入set中
        map.put(goods_id, webSocketSets);
        addOnlineCount();           //在线数加1
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String goods_id = session.getPathParameters().get("goods_id");
        CopyOnWriteArraySet<ReverseWebSocketEndpoint> webSocketSets = map.get(goods_id);
        if (webSocketSets != null)
            webSocketSets.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            sendInfo(message, session.getPathParameters().get("goods_id"));
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("来自客户端的消息:" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, String goods_id) throws IOException {
        CopyOnWriteArraySet<ReverseWebSocketEndpoint> copyOnWriteArraySet = map.get(goods_id);
        if (copyOnWriteArraySet != null) {
            for (ReverseWebSocketEndpoint item : map.get(goods_id)) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ReverseWebSocketEndpoint.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ReverseWebSocketEndpoint.onlineCount--;
    }
}