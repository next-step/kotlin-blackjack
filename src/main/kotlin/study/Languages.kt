package study

class Languages(val values: List<Language>)

class LanguagesBuilder {
    private var values: MutableList<Language> = mutableListOf()

    infix fun String.level(i: Int) {
        values.add(Language(this, i))
    }

    fun build(): Languages {
        return Languages(values)
    }
}
