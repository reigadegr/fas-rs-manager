package com.fasRs.manager.root

import android.os.Process

class Native {
    companion object {
        init {
            if (Process.myUid() == 0) {
                System.loadLibrary("backend")
            }
        }
    }

    external fun isFasRsRunning(): Boolean

    external fun getFasRsMode(): String?

    external fun setFasRsMode(mode: String)

    external fun getFasRsVersion(): String?

    external fun getFasRsApps(): Array<String>?
}
