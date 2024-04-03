package com.fasRs.manager.root

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.fasRs.manager.IRootIPC
import com.topjohnwu.superuser.ipc.RootService as LibSuService

fun getRoot(
    context: Context,
    action: (IRootIPC) -> Unit,
) {
    object : RootConnection(context) {
        override fun onServiceConnected(
            name: ComponentName,
            binder: IBinder,
        ) {
            val root = getInterface(binder)
            action(root)
        }
    }
}

@Suppress("LeakingThis")
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

    }

    override fun onServiceDisconnected(name: ComponentName) {

    }
}
