package study.dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String?,
    val skill: Skill,
    val language: Language
)

class Skill {
    private val _soft = mutableListOf<String>()
    private val _hard = mutableListOf<String>()

    val soft: List<String>
        get() = _soft
    val hard: List<String>
        get() = _hard

    fun soft(value: String) {
        _soft.add(value)
    }

    fun hard(value: String) {
        _hard.add(value)
    }
}

class Language {
    private val _map = hashMapOf<String, Int>()
    val map: Map<String, Int>
        get() = _map

    infix fun String.level(level: Int) {
        _map[this] = level
    }
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skill: Skill = Skill()
    private val language: Language = Language()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String?) {
        company = value
    }

    fun skill(block: Skill.() -> Unit) {
        skill.apply(block)
    }

    fun language(block: Language.() -> Unit) {
        language.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skill, language)
    }
}
