package study

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private val skillsBuilder = SkillsBuilder()
    private val languageBuilder = LanguageBuilder()

    fun name(value: String): PersonBuilder {
        name = value
        return this
    }

    fun company(value: String): PersonBuilder {
        company = value
        return this
    }

    fun skills(block: SkillsBuilder.() -> SkillsBuilder): PersonBuilder {
        skillsBuilder.block()
        return this
    }

    fun languages(block: LanguageBuilder.() -> LanguageBuilder): PersonBuilder {
        languageBuilder.block()
        return this
    }

    private fun SkillsBuilder.build() = skills
    private fun LanguageBuilder.build() = languages

    internal fun build(): Person = Person(name, company, skillsBuilder.build(), languageBuilder.build())
}

data class Person(
    val name: String,
    val company: String,
    val skills: List<Skill>,
    val languages: List<Language>,
)

class SkillsBuilder {
    val skills get() = _skills.toList()
    private val _skills = mutableListOf<Skill>()

    fun soft(value: String): SkillsBuilder {
        _skills.add(
            Skill(
                description = value,
                type = SkillType.Soft,
            )
        )
        return this
    }

    fun hard(value: String): SkillsBuilder {
        _skills.add(
            Skill(
                description = value,
                type = SkillType.Hard,
            )
        )
        return this
    }
}

data class Skill(val description: String, val type: SkillType)

enum class SkillType {
    Soft, Hard
}

class LanguageBuilder {
    val languages get() = _languages.toList()
    private val _languages = mutableListOf<Language>()

    infix fun String.level(that: Int): LanguageBuilder {
        _languages.add(Language(this, that))
        return this@LanguageBuilder
    }
}

data class Language(val name: String, val level: Int)

fun introduce(block: PersonBuilder.() -> PersonBuilder): Person {
    return PersonBuilder().apply { block() }.build()
}
