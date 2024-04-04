package com.fasRs.manager

enum class Mode {
    Powersave,
    Balance,
    Performance,
    Fast,
    Unknown,
    ;

    companion object {
        fun parse(mode: String): Mode {
            return when (mode) {
                "powersave" -> Powersave
                "balance" -> Balance
                "performance" -> Performance
                "fast" -> Fast
                else -> Unknown
            }
        }
    }
}
