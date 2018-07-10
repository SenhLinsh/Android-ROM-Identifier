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
public class EuiChecker extends Checker {
    @Override
    protected String getManufacturer() {
        return ManufacturerList.LETV;
    }

    @Override
    protected String[] getAppList() {
        return AppList.EUI_APPS;
    }

    @Override
    public ROM getRom() {
        return ROM.EUI;
    }

    @Override
    public ROMInfo checkBuildProp(Properties properties) {
        ROMInfo info = null;
        if (properties.containsKey(BuildPropKeyList.EUI_VERSION)) {
            String versionStr = properties.getProperty(BuildPropKeyList.EUI_VERSION);
            Matcher matcher = Pattern.compile("([\\d.]+)[^\\d]*").matcher(versionStr); // 5.9.023S
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
