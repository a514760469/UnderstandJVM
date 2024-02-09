package com.instance;

import sun.jvmstat.monitor.*;

import java.net.URISyntaxException;

/**
 * @author zhanglifeng
 * @since 2022-03-08
 */
public class JavaProcessMonitor {

    public static void main(String[] args) throws URISyntaxException, MonitorException {

        MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
        for (Integer activeVm : local.activeVms()) {
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + activeVm));
            String s = MonitoredVmUtil.mainClass(vm, true);
            System.out.println("process = " + activeVm + " " + s);
        }
    }
}
