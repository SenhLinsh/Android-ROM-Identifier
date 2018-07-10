package com.linsh.rom;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/07/06
 *    desc   :
 * </pre>
 */
@RunWith(AndroidJUnit4.class)
public class CheckApplicationTest {

    /**
     * 比较 getPackageInfo() & getInstalledApplications() & getInstalledPackages() 的快慢
     * <p>
     * 结果:
     * 在比较的包名较少(<100) 的情况下, getPackageInfo() 方法消耗的时间更少. 但随着比较数的增大,
     * 消耗时间将层线性增长.
     * <p>
     * getInstalledApplications() 和 getInstalledPackages() 都是大部分时间消耗在获取所有包名的
     * 过程中. 比较的时间耗时非常少. 所以更适合做很多包名的比较.
     * <p>
     * getInstalledApplications() 在任何时候比 getInstalledPackages() 的耗时都更少.
     */
    @Test
    public void test() {
        int times = 100;
        int count = 0;
        Context appContext = InstrumentationRegistry.getTargetContext();
        PackageManager manager = appContext.getPackageManager();

        String pck = "com.miui.xxx";
        long start = System.currentTimeMillis();

        // getPackageInfo
        for (int i = 0; i < times; i++) {
            try {
                if (manager.getPackageInfo(pck, 0) != null) {
                    count++;
                }
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }
        long cost1 = System.currentTimeMillis() - start;
        Log.i(CheckApplicationTest.class.getSimpleName(), "getPackageInfo cost : " + cost1);

        // getInstalledApplications
        start = System.currentTimeMillis();
        List<ApplicationInfo> appInfos = manager.getInstalledApplications(0);
        HashSet<String> set2 = new HashSet<>();
        for (ApplicationInfo appInfo : appInfos) {
            set2.add(appInfo.packageName);
        }
        for (int i = 0; i < times; i++) {
            if (set2.contains(pck)) {
                count++;
            }
        }
        long cost2 = System.currentTimeMillis() - start;
        Log.i(CheckApplicationTest.class.getSimpleName(), "getInstalledApplications cost : " + cost2);

        // getInstalledPackages
        start = System.currentTimeMillis();
        List<PackageInfo> pckInfos = manager.getInstalledPackages(0);
        HashSet<String> set3 = new HashSet<>();
        for (PackageInfo pckInfo : pckInfos) {
            set3.add(pckInfo.packageName);
        }
        for (int i = 0; i < times; i++) {
            if (set3.contains(pck)) {
                count++;
            }
        }
        long cost3 = System.currentTimeMillis() - start;
        Log.i(CheckApplicationTest.class.getSimpleName(), "getInstalledPackages cost : " + cost3);

        Assert.assertEquals(0, count);
    }
}
