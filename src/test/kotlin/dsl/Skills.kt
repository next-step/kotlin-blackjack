package dsl

class Skills(
    val softs: List<String>,
    val hards: List<String>,
) {
    companion object {
        fun empty(): Skills = Skills(emptyList(), emptyList())
    }
}

class SkillsBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(skill: String) {
        softSkills.add(skill)
    }

    fun hard(skill: String) {
        hardSkills.add(skill)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}
