package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Map<String, Int>
)

data class Skills(
    val softSkills: List<String>,
    val hardSkills: List<String>
)

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Map<String, Int>

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
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

class SkillsBuilder {
    private val softSkills: MutableList<String> = mutableListOf()
    private val hardSkills: MutableList<String> = mutableListOf()

    fun soft(hardSkill: String) {
        softSkills.add(hardSkill)
    }

    fun hard(hardSkill: String) {
        hardSkills.add(hardSkill)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

class LanguagesBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(other: Int) {
        languages[this] = other
    }

    fun build(): Map<String, Int> {
        return languages
    }
}
