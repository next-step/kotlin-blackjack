package study

class LanguagesBuilder {
    private var languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
