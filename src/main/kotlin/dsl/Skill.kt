package dsl

enum class SkillType {

    SOFT, HARD;
}

data class Skill(val type: SkillType, val name: String)

class SkillsBuilder {

    private val skills = mutableListOf<Skill>()

    fun softSkill(name: String) {
        skills.add(Skill(SkillType.SOFT, name))
    }

    fun hardSkill(name: String) {
        skills.add(Skill(SkillType.HARD, name))
    }

    fun build(): List<Skill> = skills
}

fun skills(block: SkillsBuilder.() -> Unit) =
    SkillsBuilder().apply(block).build()
