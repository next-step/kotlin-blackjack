package study.domain

class Languages {
    private var languages = mutableListOf<Language>()

    infix fun String.level(level: Int): Languages {
        languages.add(Language(this, level))
        return this@Languages
    }

    operator fun invoke(): List<Language> = languages.toList()
}
