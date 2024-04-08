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
            return native.getFasRsMode() ?: "failed"
        }

        override fun setFasRsMode(mode: String) {
            return native.setFasRsMode(mode)
        }

        override fun getFasRsVersion(): String {
            return native.getFasRsVersion() ?: "failed"
        }

        override fun getFasRsApps(): ArrayList<String> {
            return ArrayList((native.getFasRsApps() ?: emptyArray()).toList())
        }
    }

    override fun onBind(intent: Intent): IRootIPC.Stub {
        return RootIPC()
    }
}
