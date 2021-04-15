package study

class Person {
    var name: String? = null
        private set
    var company: String? = null
        private set

    private var _skills = Skills()

    private var _languages = Languages()

    var skills = emptyList<Skill>()
        get() = _skills.skills

    var languages = emptyList<Language>()
        get() = _languages.languages

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(init: List<Skill>.() -> Unit): List<Skill> {
        _skills.skills.init()
        return _skills.skills.toList()
    }

    fun languages(init: List<Language>.() -> Unit): List<Language> {
        _languages.languages.init()
        return _languages.languages.toList()
    }

    fun soft(name: String) {
        _skills.add(Skill(name))
    }

    fun hard(name: String) {
        _skills.add(Skill(name))
    }

    infix fun String.level(i: Int) {
        _languages.add(Language(this, i))
    }
}

fun introduce(init: Person.() -> Unit): Person {
    val person = Person()
    person.init()
    return person
}
