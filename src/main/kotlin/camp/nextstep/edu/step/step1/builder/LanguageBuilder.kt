package camp.nextstep.edu.step.step1.builder

import camp.nextstep.edu.step.step1.domain.Language

class LanguageBuilder {
    private var languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }

}
