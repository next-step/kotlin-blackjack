package dsl_study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = listOf()
    private var languages: List<Language> = listOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(
            name,
            company,
            skills.toList(),
            languages.toList(),
        )
    }
}

class LanguagesBuilder(
    private val languages: MutableList<Language> = mutableListOf(),
) {
    infix fun String.level(level: Int) {
        val language = Language(this, level)
        languages.add(language)
    }

    fun build(): List<Language> = languages.toList()
}

data class Language(
    val name: String,
    val level: Int,
)

class SkillsBuilder(
    private val skills: MutableList<Skill> = mutableListOf(),
) {
    fun soft(skillName: String) {
        val softSkill = Skill.createSoftSkill(skillName)
        skills.add(softSkill)
    }

    fun hard(skillName: String) {
        val hardSkill = Skill.createHardSkill(skillName)
        skills.add(hardSkill)
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: List<Language>,
)

data class Skill(
    val type: Type,
    val name: String,
) {

    companion object {
        fun createSoftSkill(skillName: String): Skill {
            return Skill(Type.SOFT, skillName)
        }

        fun createHardSkill(skillName: String): Skill {
            return Skill(Type.HARD, skillName)
        }
    }

    enum class Type {
        SOFT,
        HARD
        ;
    }
}
