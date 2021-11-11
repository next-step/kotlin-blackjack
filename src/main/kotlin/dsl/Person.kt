package dsl

class Person {
    lateinit var name: Name
    lateinit var company: Company
    var skills: Skills = Skills()
    var languages: Languages = Languages()

    fun name(name: String) = name.also { this.name = Name(it) }

    fun company(company: String) = company.also { this.company = Company(it) }

    fun skills(initializer: Skills.() -> Unit) = skills.apply(initializer)
    fun languages(initializer: Languages.() -> Unit) = languages.apply(initializer)
}

fun introduce(initializer: Person.() -> Unit): Person = Person().apply(initializer)
