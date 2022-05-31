package dsl

data class Resume(val name: String, val company: String, val skills: Skills, val languages: Languages)

class ResumeBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills(emptyList(), emptyList())
    private var languages: Languages = Languages(emptyMap())

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
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}

data class Skills(val soft: List<String>, val hard: List<String>)
class SkillsBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(skill: String) {
        soft.add(skill)
    }

    fun hard(skill: String) {
        hard.add(skill)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

data class Languages(private val levels: Map<String, Int>) : Map<String, Int> by levels
class LanguagesBuilder {
    private val levels = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        levels[this] = level
    }

    fun build(): Languages {
        return Languages(levels)
    }
}
