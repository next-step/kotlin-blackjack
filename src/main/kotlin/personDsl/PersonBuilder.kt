package personDsl

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

class PersonBuilder :
    SkillsBuilder,
    LanguagesBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val softSkills: MutableList<String> = mutableListOf()
    private val hardSkills: MutableList<String> = mutableListOf()
    private val languages: MutableList<LanguageLevel> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        block()
    }

    override fun soft(softSkill: String) {
        softSkills.add(softSkill)
    }

    override fun hard(hardSkill: String) {
        hardSkills.add(hardSkill)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        block()
    }

    override fun String.to(level: Int) {
        languages.add(LanguageLevel(this, level))
    }

    fun build(): Person = Person(name = name, company = company, softSkills = softSkills, hardSkills = hardSkills, languages = languages)
}

interface SkillsBuilder {
    fun soft(softSkill: String)

    fun hard(hardSkill: String)
}

interface LanguagesBuilder {
    infix fun String.to(level: Int)
}
