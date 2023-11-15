package study.builder

import study.domain.Language

class LangaugeBuilder {
    private var languages: List<Language> = emptyList()

    infix fun String.level(level: Int) {
        languages = languages + Language(this, level)
    }

    fun build(): List<Language> {
        return languages
    }
}
