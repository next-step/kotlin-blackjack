fun person(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String?,
    val company: String?,
    val skills: List<Skill>?,
    val languages: List<Language>?
)

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: List<Skill>? = null
    private var languages: List<Language>? = null

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

sealed class Skill(val desc: String) {
    class Soft(desc: String) : Skill(desc)
    class Hard(desc: String) : Skill(desc)
}

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill.Soft(value))
    }

    fun hard(value: String) {
        skills.add(Skill.Hard(value))
    }

    fun build(): List<Skill> {
        return skills
    }
}

data class Language(
    val kind: String,
    val level: Int
)

class LanguagesBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }
}
