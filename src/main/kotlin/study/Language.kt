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

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        val type = Language.Type.values().first { it.name.lowercase() == this.lowercase() }
        languages.add(Language(type, value))
    }

    fun build() = languages.toList()
}
