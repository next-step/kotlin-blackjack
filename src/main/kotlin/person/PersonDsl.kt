package person

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Map<String, Int>,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()
    private var languages: Map<String, Int> = mapOf()

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

    fun build(): Person = Person(name, company, skills, languages)
}

data class Skills(
    val soft: List<String> = emptyList(),
    val hard: List<String> = emptyList(),
)

class SkillsBuilder {
    private var softSkills: List<String> = emptyList()
    private var hardSkills: List<String> = emptyList()

    fun soft(value: String) {
        softSkills = softSkills + value
    }

    fun hard(value: String) {
        hardSkills = hardSkills + value
    }

    fun build(): Skills = Skills(softSkills, hardSkills)
}

class LanguagesBuilder {
    private var languageLevels: Map<String, Int> = mapOf()

    infix fun String.level(value: Int) {
        languageLevels = languageLevels.plus(this to value)
    }

    fun build(): Map<String, Int> = languageLevels
}
