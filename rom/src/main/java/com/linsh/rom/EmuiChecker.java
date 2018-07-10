package com.linsh.rom;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/07/09
 *    desc   :
 * </pre>
 */
public class EmuiChecker extends Checker {
    @Override
    protected String getManufacturer() {
        return ManufacturerList.HUAWEI;
    }

    @Override
    protected String[] getAppList() {
        return AppList.EMUI_APPS;
    }

    @Override
    public ROM getRom() {
        return ROM.EMUI;
    }


    @Override
    public ROMInfo checkBuildProp(RomProperties properties) throws Exception {
        ROMInfo info = null;
        String versionStr = properties.getProperty(BuildPropKeyList.EMUI_VERSION);
        if (!TextUtils.isEmpty(versionStr)) {
            Matcher matcher = Pattern.compile("EmotionUI_([\\d.]+)").matcher(versionStr); // EmotionUI_3.0
            if (matcher.find()) {
                try {
                    String version = matcher.group(1);
                    info = new ROMInfo(getRom());
                    info.setVersion(version);
                    info.setBaseVersion(Integer.parseInt(version.split("\\.")[0]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
}
