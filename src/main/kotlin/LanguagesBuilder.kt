class LanguagesBuilder {
    private val languageAndLevel = mutableMapOf<String, Int>()

    infix fun String.level(score: Int) {
        languageAndLevel[this] = score
    }

    fun build(): Map<String, Int> = languageAndLevel
}