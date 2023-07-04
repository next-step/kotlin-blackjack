package study

data class Language(val type: Type, val level: Int) {
    enum class Type {
        KOREAN, ENGLISH
    }

    companion object {
        fun korean(level: Int) = Language(Type.KOREAN, level)
        fun english(level: Int) = Language(Type.ENGLISH, level)
    }
}
