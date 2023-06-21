package study

class LanguageBuilder {
    private var language: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        language[this] = level
    }

    fun build() = language.toMap()
}
