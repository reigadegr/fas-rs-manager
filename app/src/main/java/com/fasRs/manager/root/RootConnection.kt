package com.fasRs.manager.root

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.fasRs.manager.IRootIPC
import com.topjohnwu.superuser.ipc.RootService as LibSuService

class RootConnection(context: Context) : ServiceConnection {
    var ipc: IRootIPC? = null

    init {
        val intent = Intent(context, this.javaClass)
        LibSuService.bind(intent, this)
    }

    fun aidl() : IRootIPC? {
        return ipc
    }

    override fun onServiceConnected(name: ComponentName, binder: IBinder) {
        ipc = IRootIPC.Stub.asInterface(binder)
        ipc?.checkConnection()
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        TODO("Not yet implemented")
    }
}