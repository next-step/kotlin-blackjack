package dsl

data class Person(
    var name: String = "",
    var company: String = "",
    val skills: Skills = Skills(),
    val languages: Languages = Languages(),
) {
    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }
}

fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}

fun Person.skills(block: Skills.() -> Unit) {
    this.skills.apply(block)
}

fun Person.languages(block: Languages.() -> Unit) {
    this.languages.apply(block)
}
