package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var nameValue: String
    private var companyValue: String? = null
    private val skills: MutableList<Skill> = mutableListOf()
    private val language: MutableList<Language> = mutableListOf()

    fun name(value: String) {
        nameValue = value
    }

    fun company(value: String) {
        companyValue = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills.addAll(SkillBuilder().apply(block).build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        language.addAll(LanguageBuilder().apply(block).build())
    }

    fun build(): Person {
        require(::nameValue.isInitialized) { "이름이 초기화되지 않았습니다." }

        return Person(nameValue, companyValue, skills, language)
    }
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill.Soft(description))
    }

    fun hard(description: String) {
        skills.add(Skill.Hard(description))
    }

    fun build(): List<Skill> = skills
}


class LanguageBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) = languages.add(Language(this, level))

    fun build(): List<Language> = languages
}
