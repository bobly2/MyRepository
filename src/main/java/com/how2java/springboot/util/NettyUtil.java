package com.how2java.springboot.util;

import io.netty.channel.Channel;

import java.net.SocketAddress;


public class NettyUtil {

    /**
     * 获取Channel的远程IP地址
     * @param channel
     * @return
     */
    public static String parseChannelRemoteAddr(final Channel channel) {
        if (null == channel) {
            return "";
        }
        //没有协议的地址
        SocketAddress remote = channel.remoteAddress();//与channel连接的远程地址
        final String addr =       remote != null  ?  remote.toString() : "";//条件表达式？表达式1：表达式2

        if (addr.length() > 0) {
            int index = addr.lastIndexOf("/");//返回指定字符在此字符串中最后一次出现处的索引 1 or 0
            if (index >= 0) {
                return addr.substring(index + 1);//返回 //后的地址
            }

            return addr;
        }

        return "";
    }
}
