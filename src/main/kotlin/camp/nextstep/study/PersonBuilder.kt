package camp.nextstep.study

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills = Skills(listOf())
    private var languages = Languages(mapOf())

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(skillBuildBlock: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(skillBuildBlock).build()
    }

    fun languages(languagesBuildBlock: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(languagesBuildBlock).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(personBuildBlock: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(personBuildBlock).build()
}