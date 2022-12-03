package study


class LanguagesBuilder {
    private var targets: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        targets.add(Language(this, level))
    }

    fun build(): List<Language> {
        return targets
    }

}


data class Language(val type: String, val level: Int)
