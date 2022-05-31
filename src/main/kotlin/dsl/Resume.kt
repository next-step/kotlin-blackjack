package dsl

data class Resume(val name: String, val company: String, val skills: Skills)

class ResumeBuilder {
    var name: String = ""
    var company: String = ""
    var skills: Skills = Skills(emptyList(), emptyList())

    fun name(name: String) {
        this.name = name
    }

    fun company(name: String) {
        this.company = name
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun build(): Resume {
        return Resume(name, company, skills)
    }
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}

class Skills(val soft: List<String>, val hard: List<String>)
class SkillsBuilder {
    val soft = mutableListOf<String>()
    val hard = mutableListOf<String>()

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
