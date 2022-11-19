package step1

class LanguageBuilder(
    private val value: MutableList<Pair<String, Int>> = mutableListOf()
) {
    infix fun String.level(level: Int) {
        value.add(this to level)
    }

    fun build(): Language {
        return Language(value)
    }
}
