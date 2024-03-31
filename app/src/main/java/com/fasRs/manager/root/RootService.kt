package com.fasRs.manager.root

import android.content.Intent
import com.fasRs.manager.IRootIPC
import com.topjohnwu.superuser.ipc.RootService as LibSuService

class RootService : LibSuService() {
    private class RootIPC : IRootIPC.Stub() {
        override fun checkConnection(): String {
            return "Hello su"
        }
    }

    override fun onBind(p0: Intent): IRootIPC.Stub {
        return RootIPC()
    }
}
