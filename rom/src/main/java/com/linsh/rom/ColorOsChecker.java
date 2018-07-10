package com.linsh.rom;

import android.text.TextUtils;

import java.util.Properties;
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
public class ColorOsChecker extends Checker {

    @Override
    protected String getManufacturer() {
        return ManufacturerList.OPPO;
    }

    @Override
    protected String[] getAppList() {
        return AppList.COLOR_OS_APPS;
    }

    @Override
    public ROM getRom() {
        return ROM.ColorOS;
    }

    @Override
    public ROMInfo checkBuildProp(Properties properties) {
        ROMInfo info = null;
        if (properties.containsKey(BuildPropKeyList.COLOROS_ROM_VERSION)) {
            String versionStr = properties.getProperty(BuildPropKeyList.COLOROS_ROM_VERSION);
            Matcher matcher = Pattern.compile("ColorOS([\\d.]+)").matcher(versionStr); // ColorOS2.1
            if (!TextUtils.isEmpty(versionStr) && matcher.find()) {
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
