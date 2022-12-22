package study

@JvmInline
value class Language(val name: String)

@JvmInline
value class Grade(val value: Int) {
    init {
        check(value >= 0) {
            throw IllegalArgumentException("등급은 음수일 수 없습니다.")
        }
    }
}

class Languages(
    val languages: Map<Language, Grade>
)

class LanguageBuilder {
    private val languages = hashMapOf<Language, Grade>()

    infix fun String.level(grade: Int) {
        languages[Language(this)] = Grade(grade)
    }

    fun build(): Languages {
        return Languages(languages.toMap())
    }
}
