package com.how2java.springboot.handler;

import com.alibaba.fastjson.JSONObject;
import com.how2java.springboot.pojo.UserInfo;
import com.how2java.springboot.proto.ChatCode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {//客户端的业务Handler
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    //发送信息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame)
            throws Exception {
        UserInfo userInfo = UserInfoManager.getUserInfo(ctx.channel());//返回channel  通过channel得到用户信息。
        if (userInfo != null && userInfo.isAuth()) {//isAuth 认证
            JSONObject json = JSONObject.parseObject(frame.text());//用json格式
            // 广播返回用户发送的消息文本
            UserInfoManager.broadcastMess(userInfo.getUserId(), userInfo.getNick(), json.getString("mess"));
        }
    }
    //关闭通道
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        UserInfoManager.removeChannel(ctx.channel());
        UserInfoManager.broadCastInfo(ChatCode.SYS_USER_COUNT,UserInfoManager.getAuthUserCount());
        super.channelUnregistered(ctx);
    }
    //连接错误
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("connection error and close the channel", cause);
        UserInfoManager.removeChannel(ctx.channel());//
        UserInfoManager.broadCastInfo(ChatCode.SYS_USER_COUNT, UserInfoManager.getAuthUserCount());
    }

}
