package dsl

data class Person(
    val name: String,
    val company: String?,
    val skill: Skill?,
    val language: Language? = null,
)

class PersonBuilder {
    var name: String = ""
    var company: String? = null
    var skill: Skill? = null
    var language: Language? = null

    fun build(): Person = Person(name, company, skill, language)
}

inline fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

data class Skill(
    val softSkills: List<String>,
    val hardSkills: List<String>,
)

class SkillBuilder {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(skill: String) {
        softSkills.add(skill)
    }

    fun hard(skill: String) {
        hardSkills.add(skill)
    }

    fun build(): Skill = Skill(softSkills, hardSkills)
}

inline fun PersonBuilder.skills(block: SkillBuilder.() -> Unit): Skill {
    val skill = SkillBuilder().apply(block).build()
    this.skill = skill
    return skill
}

data class Language(
    val languages: Map<String, Int>,
)

class LanguageBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Language = Language(languages)
}

inline fun PersonBuilder.languages(block: LanguageBuilder.() -> Unit): Language {
    val language = LanguageBuilder().apply(block).build()
    this.language = language
    return language
}
