package study

fun introduce(name: String, block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder(name).apply(block).build()
}

class PersonBuilder(val name: String) {
    private var company: String? = null
    private var skills: List<Skill> = emptyList()
    private var language: List<Language> = emptyList()

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.language = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, language)
    }
}

class LanguagesBuilder(private val languages: MutableList<Language> = mutableListOf()) {
    infix fun String.level(value: Int) {
        val type = Language.Type.values().first { it.name.lowercase() == this.lowercase() }
        languages.add(Language(type, value))
    }

    fun build() = languages.toList()
}

class SkillsBuilder(private val skills: MutableList<Skill> = mutableListOf()) {
    fun soft(description: String) {
        val skill = Skill.soft(description)
        skills.add(skill)
    }

    fun hard(description: String) {
        val skill = Skill.hard(description)
        skills.add(skill)
    }

    fun build() = skills.toList()
}
