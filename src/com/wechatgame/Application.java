package com.wechatgame;

import com.wechatgame.tools.SettingToolkit;
import com.wechatgame.ui.WechatGameUI;
import com.zlw.cmd.tools.AdbToolKit;
import com.zlw.cmd.tools.LogToolKit;

/**
 * @Description
 * @Author Dee1024 <coolcooldee@gmail.com>
 * @Version 1.0
 * @Since 1.0
 * @Date 2018/1/3
 */


/**
 * 应用启动
 */
public class Application {
    public static void main(String[] args) {
        LogToolKit.println("V1.0.201801102000.stable");
        SettingToolkit.init();
        WechatGameUI.init();
    }

}
