package study.domain

class PersonBuilder {
    lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(initializer: Skills.() -> Unit) {
        skills = Skills().apply(initializer)
    }
    
    fun languages(initializer: Languages.() -> Unit){
        languages = Languages().apply(initializer)
    }

    fun build(): Person = Person(name, company, skills , languages)
}
