package org.gigas.core.server.thread;

public interface IHandleThread<T> {
	/**
	 * 增加任务
	 * @param t
	 */
	public void addTask(T t);
	/**
	 * 停止线程
	 * @param immediately 是否立即执行(不管任务是否做完)
	 */
	public void stopThread(boolean immediately);
}
