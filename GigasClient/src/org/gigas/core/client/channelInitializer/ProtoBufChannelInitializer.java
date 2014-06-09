package org.gigas.core.client.channelInitializer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

import org.gigas.core.client.handler.ProtoBufMessageHandler;

/**
 * ProtoBuf类Channel处理链初始化
 * 
 * @author hank
 * 
 */
public class ProtoBufChannelInitializer extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast(new ProtoBufMessageHandler());
	}
}
