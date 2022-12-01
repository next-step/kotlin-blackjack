package study

class SkillsBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skills {
        val soft = Skill.Soft(softSkills)
        val hard = Skill.Hard(hardSkills)
        return Skills(listOf(soft, hard))
    }
}

data class Skills(val value: List<Skill>)
sealed class Skill {
    data class Soft(val value: List<String>) : Skill()
    data class Hard(val value: List<String>) : Skill()
}
