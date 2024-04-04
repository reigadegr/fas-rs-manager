package com.fasRs.manager.root

import android.content.Intent
import com.fasRs.manager.IRootIPC
import com.fasRs.manager.PackageInfo
import com.topjohnwu.superuser.ipc.RootService as LibSuService
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.content.Context
import android.content.pm.ApplicationInfo

class RootService : LibSuService() {
    private class RootIPC(context: Context) : IRootIPC.Stub() {
        private val context = context
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
        
        override fun getAllPackages(): ArrayList<PackageInfo> {
            val packageManager = context.getPackageManager()
            val infos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            val list = infos.map { info->
                val appName = info.loadLabel(packageManager).toString()
                val pkgName = info.packageName
                val icon = (info.loadIcon(packageManager) as BitmapDrawable).bitmap
                
                PackageInfo(appName = appName, pkgName = pkgName, icon = icon)
            }
            
            return ArrayList(list)
        }
    }

    override fun onBind(intent: Intent): IRootIPC.Stub {
        val context = getApplicationContext()
        return RootIPC(context)
    }
}
