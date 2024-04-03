package com.fasRs.manager.root

import android.content.Intent
import com.fasRs.manager.IRootIPC
import com.topjohnwu.superuser.ipc.RootService as LibSuService

class RootService : LibSuService() {
    private class RootIPC : IRootIPC.Stub() {
        private val native = Native()

        override fun isFasRsRunning(): Boolean {
            return native.isFasRsRunning()
        }

        override fun getFasRsMode(): String {
            return native.getFasRsMode()?: "failed"
        }

        override fun setFasRsMode(mode: String) {
            return native.setFasRsMode(mode)
        }

        override fun getFasRsVersion(): String {
            return native.getFasRsVersion()?: "failed"
        }
    }

    override fun onBind(p0: Intent): IRootIPC.Stub {
        return RootIPC()
    }
}
