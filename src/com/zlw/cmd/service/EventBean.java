package com.zlw.cmd.service;

/**
 * 事件模型
 * @author zlw
 *
 */
public class EventBean {
	/**
	 * 描述信息
	 */
	private String description;

	/**
	 * 事件类型
	 */
	private EventType type;

	/**
	 * 起始点
	 */
	private Point p1;

	/**
	 * 终点
	 */
	private Point p2;

	/**
	 * 事件耗时 单位ms
	 */
	private int time;

	/**
	 * 延迟执行时间 单位ms
	 */
	private int delayTime;

	/**
	 * 生成点击事件模型
	 * @return
	 */
	public static EventBean newTapEvent(EventBean.Point point,
			String description) {
		EventBean eventBean = new EventBean();
		eventBean.type = EventType.TAP;
		eventBean.p1 = point;
		eventBean.description = description;
		return eventBean;
	}
	/**
	 * 生成滑动事件模型
	 * @return
	 */
	public static EventBean newSwipeEvent(Point p1, Point p2, int time,
			String description) {
		EventBean eventBean = new EventBean();
		eventBean.type = EventType.SWIPE;
		eventBean.p1 = p1;
		eventBean.p2 = p2;
		eventBean.time = time;
		eventBean.description = description;
		return eventBean;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public EventBean setDelayTime(int delayTime) {
		this.delayTime = delayTime;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public enum EventType {
		SWIPE("滑动/长按"), TAP("点击"), INPUT_TEXT("输入文本"), KEY("按键");
		/**
		 * 描述信息
		 */
		public String description;

		EventType(String description) {
			this.description = description;
		}
	}

	public static class Point {

		/**
		 * 描述信息
		 */
		public String description;

		public float x;

		public float y;

		Point(String description, float x, float y) {
			this.description = description;
			this.x = x;
			this.y = y;
		}
		Point(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

}
