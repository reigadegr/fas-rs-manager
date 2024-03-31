package com.fasRs.manager.root

import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.fasRs.manager.IRootIPC

class RootConnection(context: Context) {
    private val service = RootService()
    private val intent = Intent(context, service.javaClass)

    fun sudo(): IRootIPC.Stub {
        return service.onBind(intent)
    }
}