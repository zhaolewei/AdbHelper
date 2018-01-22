package com.zlw.cmd.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 封装ADB功能，通过执行ADB命令行实现 需要先下载 Android Debug Bridge
 * 到本地，参考地址：https://developer.android.com/studio/command-line/adb.html#forwardports
 */
public abstract class AdbToolKit {

	/**
	 * adb.exe的路径 ； 已配置环境变量的不用填写
	 */
	public static String adbPath = "";

	private final static String CMD_SCREEN_CAP_METHOD1 = "${adbpath} adb exec-out screencap -p > ${imagename}";
	private final static String CMD_SCREEN_CAP_METHOD2_1 = "${adbpath} adb shell screencap -p /sdcard/${imagename}";
	private final static String CMD_SCREEN_CAP_METHOD2_2 = "${adbpath} adb pull /sdcard/${imagename} > .";

	/**
	 * 输入文本
	 */
	private final static String CMD_INPUT_TEXT = "${adbpath} adb shell input text ${text}";

	/**
	 * 点击事件
	 */
	private final static String CMD_SCREEN_CLICK = "${adbpath} adb shell input tap ${x} ${y}";

	/**
	 * 屏幕的滑动或长按事件
	 */
	private final static String CMD_SCREEN_SWIPE = "${adbpath} adb shell input swipe ${x1} ${y1} ${x2} ${y2} ${time}";

	/**
	 * adb devices: 查看设备连接状态
	 */
	private final static String CMD_DEVICES = "${adbpath} adb devices";

	/**
	 * 执行命令
	 * 
	 * @param cmd
	 *            命令
	 * @return 输出结果 -1： 内部错误 -2： 运行错误 其他正常
	 */
	public static String cmder(String cmd) {
		if (cmd == null || "".equals(cmd)) {
			LogToolKit.println("命令不能为空");
			return null;
		}
		LogToolKit.println("执行命令： " + cmd);

		Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try (InputStream is = process.getInputStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is))) {
			String line;
			List<String> lineList = new ArrayList<String>();
			StringBuilder resultBuff = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				lineList.add(line);
				resultBuff.append(line);
				resultBuff.append("\n");
			}

			return resultBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取已连接连接设备的SN
	 * 
	 * @return 已连接连接设备的SN
	 */
	public static String[] getAllDevices() {
		String cmd = CMD_DEVICES.replace("${adbpath}", adbPath);

		String result = cmder(cmd);
		String[] arrayStr = result.split("\n");
		List<String> devicesList = new ArrayList<>();

		for (String str : arrayStr) {
			if (str.trim().startsWith("List")) {
				continue;
			}

			if (str.endsWith("device")) {
				String device = str.trim().split("\t")[0];
				devicesList.add(device);
			} else {
				LogToolKit.println("请检查设备授权状态：" + str);
			}
		}

		return (String[]) devicesList.toArray(new String[devicesList.size()]);
	}

	/**
	 * 模拟点击
	 */
	public static void adbTap(float x, float y) {
		String cmd = CMD_SCREEN_CLICK.replace("${adbpath}", adbPath)
				.replace("${x}", x + "").replace("${y}", y + "");
		cmder(cmd);
	}

	/**
	 * 模拟输入文本
	 */
	public static void adbText(String text) {
		String cmd = CMD_INPUT_TEXT.replace("${adbpath}", adbPath)
				.replace("${text}", text);
		cmder(cmd);
	}

	/**
	 * 模拟滑动
	 * 
	 * @param x1 起始坐标X
	 * @param y1 起始坐标Y
	 * @param x2 终点坐标X
	 * @param y2 终点坐标Y
	 * @param time 长按时间
	 */
	public static void adbSwipe(float x1, float y1, float x2, float y2,
			int time) {
		String cmd = CMD_SCREEN_SWIPE.replace("${adbpath}", adbPath)
				.replace("${x1}", x1 + "").replace("${y1}", y1 + "")
				.replace("${x2}", x2 + "").replace("${y2}", y2 + "")
				.replace("${time}", time + "");
		cmder(cmd);
	}

	public static void main(String[] args) throws IOException {
		// String[] result = checkAdbAndDevice();
		// LogToolKit.println("已连接的设备:");
		// for (String str : result) {
		// LogToolKit.println(str);
		// }

		// clickPoint(400, 950);

		// adbText("input");

		adbSwipe(100, 300, 100, 600, 10000);
	}

}
