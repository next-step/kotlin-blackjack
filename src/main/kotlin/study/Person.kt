package study

class Person {
    val skills: List<Skill>
        get() = _skills.skills

    val languages: Map<String, Int>
        get() = _languages.languages

    var name: String? = null
        private set
    var company: String? = null
        private set

    private var _skills = Skills()

    private var _languages = Languages()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(init: Skills.() -> Unit) {
        _skills.init()
    }

    fun languages(init: Languages.() -> Unit) {
        _languages.init()
    }
}

fun introduce(init: Person.() -> Unit): Person {
    val person = Person()
    person.init()
    return person
}
