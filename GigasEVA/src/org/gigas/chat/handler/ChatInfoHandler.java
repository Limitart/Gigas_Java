// Generated by the gigasGenerator.  ONLY CAN TODO 'handleMessage' Method!

package org.gigas.chat.handler;

import java.util.List;

import org.gigas.chat.message.proto.ChatMessageFactory.ChatInfo;
import org.gigas.chat.message.proto.ChatMessageFactory.RoleChatInfo;
import org.gigas.core.server.handler.IHandler;

import com.google.protobuf.MessageLite;

public class ChatInfoHandler extends IHandler {

	@Override
	public void handleMessage(MessageLite message) {
		ChatInfo msg = (ChatInfo) message;
		String content = msg.getContent();
		List<Integer> integerListList = msg.getIntegerListList();
		long number = msg.getNumber();
		System.out.println("content->" + content + " number->" + number);
		for (int i : integerListList) {
			System.out.print(i + ",");
		}
		System.out.println();
		RoleChatInfo roleChatInfo = msg.getRoleChatInfo();
		String name = roleChatInfo.getName();
		int level = roleChatInfo.getLevel();
		long roleId = roleChatInfo.getRoleId();
		boolean sex = roleChatInfo.getSex();
		System.out.println("name->" + name + " level->" + level + " roleId->" + roleId + " sex->" + sex);
	}
}