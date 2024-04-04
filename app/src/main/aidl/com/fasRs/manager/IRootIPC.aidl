// IRootIPC.aidl
package com.fasRs.manager;

import com.fasRs.manager.PackageInfo;

parcelable PackageInfo;

interface IRootIPC {
    boolean isFasRsRunning();
    String getFasRsMode();
    void setFasRsMode(String mode);
    String getFasRsVersion();
    List<PackageInfo> getAllPackages();
}