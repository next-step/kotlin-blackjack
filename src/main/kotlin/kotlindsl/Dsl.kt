package kotlindsl

class Skills {
    private val _soft = mutableListOf<String>()

    val soft: List<String>
        get() = _soft.toList()

    private val _hard = mutableListOf<String>()
    val hard: List<String>
        get() = _hard.toList()


    fun soft(skill: String) {
        _soft.add(skill)
    }

    fun hard(skill: String) {
        _hard.add(skill)
    }
}

class Languages {
    private val _languages = mutableListOf<Pair<String, Int>>()

    val languages: List<Pair<String, Int>>
        get() = _languages.toList()

    infix fun String.level(lv: Int) {
        _languages.add(Pair(this, lv))
    }
}

data class Person(val name: String, val company: String, val skills: Skills, val languages: Languages)

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: Skills = Skills();
    private var languages: Languages = Languages();

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: Skills.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        languages.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
