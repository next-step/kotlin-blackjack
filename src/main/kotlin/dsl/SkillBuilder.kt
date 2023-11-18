package dsl

class SkillBuilder {
    private val skills: MutableList<Skill> = ArrayList()

    fun soft(name: String) {
        skills.add(Skill(name, SkillType.SOFT))
    }

    fun hard(name: String) {
        skills.add(Skill(name, SkillType.HARD))
    }

    fun build(): List<Skill> {
        return skills
    }
}

fun skills(block: SkillBuilder.() -> Unit): List<Skill> =
    SkillBuilder().apply(block).build()
