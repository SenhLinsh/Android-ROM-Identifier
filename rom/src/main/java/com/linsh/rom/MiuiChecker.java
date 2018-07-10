package com.linsh.rom;

import android.text.TextUtils;

import java.util.Properties;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/07/01
 *    desc   :
 * </pre>
 */
class MiuiChecker extends Checker {

    @Override
    public ROM getRom() {
        return ROM.MIUI;
    }

    @Override
    protected String getManufacturer() {
        return ManufacturerList.XIAOMI;
    }

    @Override
    protected String[] getAppList() {
        return AppList.MIUI_APPS;
    }

    @Override
    public ROMInfo checkBuildProp(Properties properties) {
        ROMInfo info = null;
        if (properties.containsKey(BuildPropKeyList.MIUI_VERSION_NANE)) {
            String versionName = properties.getProperty(BuildPropKeyList.MIUI_VERSION_NANE);
            if (!TextUtils.isEmpty(versionName) && versionName.matches("[Vv]\\d+")) { // V9
                try {
                    info = new ROMInfo(getRom());
                    info.setBaseVersion(Integer.parseInt(versionName.substring(1)));

                    String versionStr = properties.getProperty(BuildPropKeyList.MIUI_VERSION);
                    if (!TextUtils.isEmpty(versionStr) && versionStr.matches("[\\d.]+")) { // 8.1.25
                        info.setVersion(versionStr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }
}
