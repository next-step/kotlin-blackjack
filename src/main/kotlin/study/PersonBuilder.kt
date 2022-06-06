package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills(SoftSkills(listOf()), HardSkills(listOf()))
    private var languages: Languages = Languages(listOf())

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(this.name, this.company, this.skills, this.languages)
    }
}
