package dsl

data class Resume(val name: String, val company: String?, val skills: Skills?, val languages: Languages?)

class ResumeBuilder {
    private var name: String = NO_NAME
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(name: String) {
        this.company = name
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Resume {
        return Resume(name, company, skills, languages)
    }

    companion object {
        const val NO_NAME = ""
    }
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}
