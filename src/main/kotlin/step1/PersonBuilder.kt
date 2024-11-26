package step1

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: MutableList<String>? = null
    private var languages: MutableList<String>? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build().toMutableList()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build().toMutableList()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private val skills = mutableListOf<String>()

    fun soft(value: String) {
        skills.add(value)
    }

    fun hard(value: String) {
        skills.add(value)
    }

    fun build(): List<String> {
        return skills
    }
}

class LanguagesBuilder {
    private val languages = mutableListOf<String>()

    infix fun String.level(level: Int) {
        languages.add("$this: $level")
    }

    fun build(): List<String> {
        return languages
    }
}
