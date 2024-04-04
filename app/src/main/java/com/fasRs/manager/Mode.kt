package com.fasRs.manager

enum class Mode {
    POWERSAVE,
    BALANCE,
    PERFORMANCE,
    FAST,
    UNKNOWN,
    ;

    companion object {
        fun parse(mode: String): Mode {
            return when (mode) {
                "powersave" -> Mode.POWERSAVE
                "balance" -> Mode.BALANCE
                "performance" -> Mode.PERFORMANCE
                "fast" -> Mode.FAST
                else -> Mode.UNKNOWN
            }
        }
    }
}
