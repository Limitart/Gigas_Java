package org.gigas.core.client.thread;

import io.netty.channel.Channel;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gigas.core.client.BaseClient;
import org.gigas.core.client.message.ProtoBufMessage;
import org.gigas.core.client.thread.ithread.IThread;
import org.gigas.core.client.utils.ChannelUtil;

import com.google.protobuf.MessageLite;

/**
 * ProtoBuf消息发送线程
 * 
 * @author hank
 * 
 */
public class ProtoBufBasedMessageSenderThread extends Thread implements IThread {
	private static Logger log = LogManager.getLogger(ProtoBufBasedMessageSenderThread.class);
	private LinkedBlockingQueue<ProtoBufMessage> senderQueue = new LinkedBlockingQueue<>();
	private boolean stop = true;
	private BaseClient client;

	public ProtoBufBasedMessageSenderThread(String name, BaseClient client) {
		this.setName(name);
		this.client = client;
	}

	@Override
	public void run() {
		stop = false;
		while (!stop || !senderQueue.isEmpty()) {
			ProtoBufMessage poll = senderQueue.poll();
			if (poll == null) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						log.error(e, e);
					}
				}
			} else {
				try {
					List<Channel> channelList = poll.getSendChannelList();
					if (channelList != null && channelList.size() > 0) {
						MessageLite build = poll.build();
						if (build == null) {
							continue;
						}
						for (Channel channel : channelList) {
							ChannelUtil.sendMessage_Protobuf_immediately(client, channel, poll);
						}
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}
	}

	@Override
	public void stopThread(boolean immediately) {
		stop = true;
		if (immediately) {
			senderQueue.clear();
		}
		synchronized (this) {
			notify();
		}
	}

	@Override
	public void addTask(Object t) {
		if (!(t instanceof ProtoBufMessage)) {
			return;
		}
		ProtoBufMessage message = (ProtoBufMessage) t;
		senderQueue.add(message);
		synchronized (this) {
			notify();
		}
	}
}
