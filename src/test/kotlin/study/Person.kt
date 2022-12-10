package study

import study.Skill.Hard
import study.Skill.Soft

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)

class PersonBuilder {
    private lateinit var name: String

    private var company: String? = null

    private var skills: Skills? = null

    private var languages: Languages? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

sealed class Skill {
    abstract val value: String

    data class Soft(override val value: String) : Skill()
    data class Hard(override val value: String) : Skill()
}

data class Skills(
    val value: MutableList<Skill> = mutableListOf()
) {
    fun add(skill: Skill) = value.add(skill)
}

class SkillBuilder {
    private val skills: Skills = Skills()

    fun soft(value: String) {
        skills.add(Soft(value))
    }

    fun hard(value: String) {
        skills.add(Hard(value))
    }

    fun build(): Skills = skills
}

class LanguageBuilder {
    private val languages: Languages = Languages()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages = languages
}

data class Language(private val language: String, private val level: Int)

data class Languages(
    val value: MutableList<Language> = mutableListOf()
) {
    fun add(language: Language) = this.value.add(language)
}


