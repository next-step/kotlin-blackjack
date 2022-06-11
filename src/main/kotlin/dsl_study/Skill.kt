package dsl_study

data class Skill(
    val type: Type,
    val name: String,
) {
    companion object {
        fun createSoftSkill(skillName: String): Skill {
            return Skill(Type.SOFT, skillName)
        }

        fun createHardSkill(skillName: String): Skill {
            return Skill(Type.HARD, skillName)
        }
    }

    enum class Type {
        SOFT,
        HARD
        ;
    }
}

class SkillsBuilder(
    private val skills: MutableList<Skill> = mutableListOf(),
) {
    fun soft(skillName: String) {
        val softSkill = Skill.createSoftSkill(skillName)
        skills.add(softSkill)
    }

    fun hard(skillName: String) {
        val hardSkill = Skill.createHardSkill(skillName)
        skills.add(hardSkill)
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
