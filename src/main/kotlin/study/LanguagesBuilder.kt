package study

class LanguagesBuilder {
    private var languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(i: Int) {
        languages[this] = i
    }

    fun build(): MutableMap<String, Int> {
        return languages
    }
}
