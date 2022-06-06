package kotlindsl

data class Resume(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?
)

class ResumeBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).toBuilder()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).toBuilder()
    }

    fun build(): Resume {
        return Resume(name, company, skills, languages)
    }
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}
