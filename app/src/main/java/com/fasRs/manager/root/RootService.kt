package com.fasRs.manager.root

import android.content.Intent
import android.os.IBinder
import com.fasRs.manager.IRootIPC
import com.topjohnwu.superuser.ipc.RootService as SuService

class RootService : SuService() {
    private val rootIPC = object : IRootIPC.Stub() {
        override fun checkConnection() : String {
            return "Hello su"
        }
    }

    override fun onBind(p0: Intent): IRootIPC.Stub {
        return rootIPC
    }
}