package study

interface Skill {
    val name: String
}

data class Skills(val skills: List<Skill>)

@JvmInline
value class SoftSkill(override val name: String) : Skill

@JvmInline
value class HardSkill(override val name: String) : Skill

class SkillsBuilder {
    private val skills = mutableListOf <Skill>()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}

fun skills(block: SkillsBuilder.() -> Unit): Skills {
    return SkillsBuilder().apply(block).build()
}