package com.fasRs.manager.root

class Native {
    companion object {
        init {
            System.loadLibrary("backend")
        }
    }

    external fun isFasRsRunning(): Boolean

    external fun getFasRsMode(): String

    external fun setFasRsMode(mode: String)
}
