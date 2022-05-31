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

data class Skills(val soft: List<String>?, val hard: List<String>?)

class SkillsBuilder {
    private var soft: MutableList<String>? = null
    private var hard: MutableList<String>? = null

    fun soft(skill: String) {
        if (soft == null) soft = mutableListOf()
        soft?.add(skill)
    }

    fun hard(skill: String) {
        if (hard == null) hard = mutableListOf()
        hard?.add(skill)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

data class Languages(private val levels: Map<String, Int>) : Map<String, Int> by levels

class LanguagesBuilder {
    private var levels: MutableMap<String, Int>? = null

    infix fun String.level(level: Int) {
        if (levels == null) levels = mutableMapOf()
        levels?.put(this, level)
    }

    fun build(): Languages {
        return Languages(levels ?: emptyMap())
    }
}
