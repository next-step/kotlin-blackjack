package study

class Person(
    val name: String,
    val company: String?,
    val skills: Skills = Skills(emptyList(), emptyList()),
    val languages: Map<String, Int> = emptyMap(),
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skillsBuilder = SkillsBuilder()
    private var languagesBuilder = LanguagesBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skillsBuilder.apply(block)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languagesBuilder.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skillsBuilder.build(), languagesBuilder.build())
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class LanguagesBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Map<String, Int> {
        return languages
    }
}

class Skills(val soft: List<String>, val hard: List<String>)
class SkillsBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}
