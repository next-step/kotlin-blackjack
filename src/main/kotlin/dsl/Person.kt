package dsl

class Person(val name: Name, val company: Company? = null, val skills: Skills, val languages: Languages)

class PersonBuilder {
    lateinit var name: Name
    private var company: Company? = null
    var skills: Skills = Skills()
    var languages: Languages = Languages()

    fun name(name: String) = name.also {
        this.name = Name(it)
    }

    fun company(company: String) = company.also {
        this.company = Company(it)
    }

    fun skills(initializer: Skills.() -> Unit) = skills.apply(initializer)
    fun languages(initializer: Languages.() -> Unit) = languages.apply(initializer)

    private fun build(): Person = Person(name, company, skills, languages)

    companion object {
        fun introduce(initializer: PersonBuilder.() -> Unit): Person =
            PersonBuilder().apply(initializer).build()
    }
}
