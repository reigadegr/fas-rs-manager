package com.fasRs.manager.root

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.fasRs.manager.IRootIPC
import com.topjohnwu.superuser.ipc.RootService as LibSuService

fun aidlDo(
    context: Context,
    action: (IRootIPC) -> Unit,
) {
    object : RootConnection(context) {
        override fun DoIpc(ipc: IRootIPC) {
            action(ipc)
        }
    }
}

abstract class RootConnection(context: Context) : ServiceConnection {
    init {
        val intent = Intent(context, RootService().javaClass).addCategory(LibSuService.CATEGORY_DAEMON_MODE)
        LibSuService.bind(intent, this)
    }

    abstract fun DoIpc(ipc: IRootIPC)

    override fun onServiceConnected(
        name: ComponentName,
        binder: IBinder,
    ) {
        Log.d("libsu", "binded")
        val ipc = IRootIPC.Stub.asInterface(binder)
        DoIpc(ipc)
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        Log.d("libsu", "unbinded")
    }
}
