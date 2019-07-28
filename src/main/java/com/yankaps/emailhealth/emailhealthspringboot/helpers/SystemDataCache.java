package com.yankaps.emailhealth.emailhealthspringboot.helpers;


import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/***
 * @author Yannis Kapsalas
 *
 * This simpleton class will be a Cache where the system data is stored.
 */
public class SystemDataCache {

  private static final SystemDataCache INSTANCE = new SystemDataCache();
  private static OperatingSystemMXBean OPERATING_SYSTEM_MX_BEAN;
  private String loadAveragePercentage;
  private String memoryUsage;
  private String cpuLoad;

  private SystemDataCache() {
    OPERATING_SYSTEM_MX_BEAN = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    reloadSystemDataCache();
  }

  public static SystemDataCache getInstance() {
    INSTANCE.reloadSystemDataCache();
    return INSTANCE;
  }

  private void reloadSystemDataCache(){
    loadAveragePercentage = Double.toString(OPERATING_SYSTEM_MX_BEAN.getSystemLoadAverage());
    memoryUsage = String.valueOf(OPERATING_SYSTEM_MX_BEAN.getCommittedVirtualMemorySize());
    cpuLoad = String.valueOf(OPERATING_SYSTEM_MX_BEAN.getSystemCpuLoad());
  }

  /**
   *  Getters for exposing json
   */
  public String getLoadAveragePercentage() {
    return loadAveragePercentage;
  }

  public String getMemoryUsage() {
    return memoryUsage;
  }

  public String getCpuLoad() {
    return cpuLoad;
  }
}
