package com.fasRs.manager.root

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.fasRs.manager.IRootIPC
import com.topjohnwu.superuser.ipc.RootService as LibSuService

fun getRoot(
    context: Context,
    action: (IRootIPC) -> Unit,
) {
    val connection =
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName,
                binder: IBinder,
            ) {
                val ipc = IRootIPC.Stub.asInterface(binder)
                action(ipc)
            }

            override fun onServiceDisconnected(name: ComponentName) {
            }
        }

    val intent = Intent(context, RootService().javaClass)
    LibSuService.bind(intent, connection)
}

abstract class RootConnection(context: Context) : ServiceConnection {
    init {
        val intent = Intent(context, RootService().javaClass)
        LibSuService.bind(intent, this)
    }

    fun getInterface(binder: IBinder): IRootIPC {
        return IRootIPC.Stub.asInterface(binder)
    }

    override fun onServiceConnected(
        name: ComponentName,
        binder: IBinder,
    ) {
        Log.d("libsu", "binded")
    }

    override fun onServiceDisconnected(name: ComponentName) {
        Log.d("libsu", "unbinded")
    }
}
