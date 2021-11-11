package dsl

class Person {
    lateinit var name: Name
    lateinit var company: Company
    var skills: Skills = Skills()

    fun name(name: String) = name.also { this.name = Name(it) }

    fun company(company: String) = company.also { this.company = Company(it) }

    fun skills(initialize: Skills.() -> Unit) = skills.apply(initialize)
}

fun introduce(initializer: Person.() -> Unit): Person = Person().apply(initializer)
