package com.nextstep.jngcii.dsl

data class Language(
    val kind: Kind,
    val level: Level
) {
    enum class Kind {
        KOREAN, ENGLISH;
    }

    enum class Level(val number: Int) {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);
    }
}

class LanguageBuilder {
    val languages = mutableListOf<Language>()

    infix fun Language.Kind.level(level: Language.Level) {
        languages.add(Language(this, level))
    }

    fun build() = languages.toList()
}
