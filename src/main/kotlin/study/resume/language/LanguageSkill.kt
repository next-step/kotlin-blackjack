package study.resume.language

data class LanguageSkill(
    val language: String,
    val level: Int
)

class LanguageBuilder {
    private val language: MutableList<LanguageSkill> = mutableListOf()

    infix fun String.level(level: Int) {
        language.add(LanguageSkill(this, level))
    }

    fun build(): List<LanguageSkill> {
        return language.toList()
    }
}
