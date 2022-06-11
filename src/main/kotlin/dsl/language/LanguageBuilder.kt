package dsl.language

class LanguageBuilder {
    private val explain: ArrayList<Language> = ArrayList()

    infix fun String.level(level: Int) {
        explain.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(explain)
    }
}
