package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages.toList())
    }
}

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Soft(value))
    }

    fun hard(value: String) {
        skills.add(Hard(value))
    }

    fun build(): Skills {
        return Skills(skills.toList())
    }
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var languages: Languages = Languages()
    private var skills: Skills = Skills()

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
        return Person(name, company, skills, languages)
    }
}

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages)
data class Languages(val values: List<Language> = listOf())
data class Language(val name: String, val level: Int)
data class Skills(val values: List<Skill> = listOf())

sealed class Skill(open val name: String)
data class Hard(override val name: String) : Skill(name)
data class Soft(override val name: String) : Skill(name)
