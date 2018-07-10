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
public class FlymeChecker extends Checker {

    @Override
    public ROM getRom() {
        return ROM.Flyme;
    }

    @Override
    protected String getManufacturer() {
        return ManufacturerList.MEIZU;
    }

    @Override
    protected String[] getAppList() {
        return AppList.FLYME_APPS;
    }

    @Override
    public ROMInfo checkBuildProp(Properties properties) {
        ROMInfo info = new ROMInfo(getRom());
        if (properties.containsKey(BuildPropKeyList.FLYME_DISPLAY_ID)) {
            String versionStr = properties.getProperty(BuildPropKeyList.FLYME_DISPLAY_ID);
            Matcher matcher = Pattern.compile("Flyme[^\\d]*([\\d.]+)[^\\d]*").matcher(versionStr); // Flyme OS 4.5.4.2U
            if (!TextUtils.isEmpty(versionStr) && matcher.find()) {
                try {
                    String version = matcher.group(1);
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
