package com.zlw.cmd.service;

import java.util.Iterator;

import com.zlw.cmd.tools.AdbToolKit;
import com.zlw.cmd.tools.LogToolKit;

public class EventModel {
	public static EventBean tapEvent1 = EventBean
			.newTapEvent(new EventBean.Point(1, 1), "模拟确定键");
	public static EventBean tapEvent2 = EventBean
			.newTapEvent(new EventBean.Point(1830, 195), "1.活动副本入口");
	public static EventBean tapEvent3 = EventBean
			.newTapEvent(new EventBean.Point(490, 860), "1.1.金币挑战入口");
	public static EventBean tapEvent4 = EventBean
			.newTapEvent(new EventBean.Point(950, 860), "1.2.勇者挑战入口");
	public static EventBean tapEvent5 = EventBean
			.newTapEvent(new EventBean.Point(1460, 860), "1.3.英雄挑战入口");

	// 转盘
	public static EventBean tapEvent6 = EventBean
			.newTapEvent(new EventBean.Point(960, 425), "1次转盘");

	public static EventBean tapEvent7 = EventBean
			.newTapEvent(new EventBean.Point(1220, 920), "再1次转盘");

	public static EventBean tapEvent8 = EventBean
			.newTapEvent(new EventBean.Point(425, 1635), "按钮1");

	public static EventBean tapEvent9 = EventBean
			.newTapEvent(new EventBean.Point(630, 1635), "按钮2");

	public static EventBean tapEvent10 = EventBean
			.newTapEvent(new EventBean.Point(810, 1635), "按钮3");
	/**
	 * workThread 
	 * @param eventBean
	 */
	public static void cmdEvent(EventBean eventBean) {
		try {
			switch (eventBean.getType()) {
				case TAP :
					if (eventBean.getDelayTime() > 0) {
						Thread.sleep(eventBean.getDelayTime());
					}
					AdbToolKit.adbTap(eventBean.getP1().x, eventBean.getP1().y);
					break;
				default :
					break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ------------------------业务层------------------------
	/**
	 * 事件-模拟转盘
	 * @param times 次数 
	 * 界面：转盘首页
	 */
	public static void moudelTurntable(int times) {
		new Thread() {
			@Override
			public void run() {
				super.run();
				cmdEvent(tapEvent6);
				for (int i = 0; i < times; i++) {
					LogToolKit.println("点击:" + tapEvent7.getDescription()
							+ " 次数：" + (i + 1));
					cmdEvent(tapEvent7.setDelayTime(5000));
				}
			}
		}.start();
	}

	public static void moudel2(int times) {
		for (int i = 0; i < 5; i++) {
			new Thread() {
				@Override
				public void run() {
					super.run();
					for (int i = 0; i < times; i++) {
						cmdEvent(tapEvent8);
						cmdEvent(tapEvent9);
						cmdEvent(tapEvent10);
					}
				}
			}.start();
		}

	}

	public static void main(String[] args) {
		moudel2(10000);
	}

}
