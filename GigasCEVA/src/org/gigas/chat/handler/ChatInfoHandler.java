// Generated by the gigasGenerator.  ONLY CAN TODO 'handleMessage' Method!

package org.gigas.chat.handler;

import org.gigas.chat.message.proto.ChatMessageFactory.ChatInfo;
import org.gigas.core.client.handler.ihandler.IProtobufHandler;

import com.google.protobuf.MessageLite;

public class ChatInfoHandler extends IProtobufHandler {

	@Override
	public void handleMessage(MessageLite message) {
		ChatInfo msg = (ChatInfo) message;
		String content = msg.getContent();
		String name = msg.getRoleChatInfo().getName();
		System.out.println(name + " says:" + content);
	}
}