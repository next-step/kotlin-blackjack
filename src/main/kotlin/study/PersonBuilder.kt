package study

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var softSkills: MutableList<String> = mutableListOf()
    private var hardSkills: MutableList<String> = mutableListOf()
    private var languageSkills: MutableList<Pair<String, Int>> = mutableListOf()

    fun name(name: String) {
        this.name = name
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: PersonBuilder.() -> Unit) {
        block()
    }

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        block()
    }

    infix fun String.level(level: Int) {
        languageSkills.add(Pair(this, level))
    }

    fun build(): Person {
        return Person(name, company, softSkills, hardSkills, languageSkills)
    }
}
