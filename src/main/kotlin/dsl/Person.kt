package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block)
        .build()
}

data class Person(
    val name: String,
    val company: String,
    val skills: Skills = Skills(),
    val languages: Languages = Languages(),
)

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private val skills: Skills = Skills()
    private val languages: Languages = Languages()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(init: Skills.() -> Unit) {
        skills.init()
    }

    fun languages(init: Languages.() -> Unit) {
        languages.init()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class Skills {
    private val _skills = mutableListOf<Skill>()
    val skills get() = _skills.toList()

    fun soft(value: String) {
        _skills.add(Skill(value, SkillType.SOFT))
    }

    fun hard(value: String) {
        _skills.add(Skill(value, SkillType.HARD))
    }

    class Skill(
        val name: String,
        val type: SkillType,
    )

    enum class SkillType {
        SOFT,
        HARD,
    }
}

class Languages {
    private val _languages = mutableMapOf<String, Int>()
    val languages get() = _languages.toMap()

    infix fun String.level(level: Int) {
        _languages[this] = level
    }
}
