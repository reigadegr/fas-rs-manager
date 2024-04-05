// IRootIPC.aidl
package com.fasRs.manager;

interface IRootIPC {
    boolean isFasRsRunning();
    String getFasRsMode();
    void setFasRsMode(String mode);
    String getFasRsVersion();
}