package dsl

class Resume(
    var name: String = "",
    var company: String = "",
    var skills: Skill = Skill(),
    var languages: Language = Language()
) {

    fun name(name: String) {
        this.name = name
    }

    fun company(companyName: String) {
        this.company = companyName
    }

    fun skills(block: Skill.() -> Unit) {
        skills.block()
    }

    fun languages(block: Language.() -> Unit) {
        languages.block()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resume

        if (name != other.name) return false
        if (company != other.company) return false
        if (skills != other.skills) return false
        if (languages != other.languages) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + company.hashCode()
        result = 31 * result + skills.hashCode()
        result = 31 * result + languages.hashCode()
        return result
    }
}

class Skill(
    val softSkills: MutableList<String> = mutableListOf(),
    val hardSkills: MutableList<String> = mutableListOf()
) {

    fun soft(skill: String) {
        softSkills.add(skill)
    }

    fun hard(skill: String) {
        hardSkills.add(skill)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Skill

        if (softSkills != other.softSkills) return false
        if (hardSkills != other.hardSkills) return false

        return true
    }

    override fun hashCode(): Int {
        var result = softSkills.hashCode()
        result = 31 * result + hardSkills.hashCode()
        return result
    }
}

class Language(
    val languages: MutableMap<String, Int> = mutableMapOf()
) {

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Language

        return languages == other.languages
    }

    override fun hashCode(): Int {
        return languages.hashCode()
    }
}

fun introduce(block: Resume.() -> Unit): Resume {
    val resume = Resume()
    resume.block()
    return resume
}
