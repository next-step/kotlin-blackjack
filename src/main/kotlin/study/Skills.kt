package study

class Skills {

    private val softSkills = mutableListOf<Skill>()
    private val hardSkills = mutableListOf<Skill>()

    fun soft(skill: String): Skills {
        softSkills.add(Skill(skill))
        return this
    }

    fun hard(skill: String): Skills {
        hardSkills.add(Skill(skill))
        return this
    }

    override fun toString(): String {
        return "I have soft skills: $softSkills, hard skills: $hardSkills"
    }
}

@JvmInline
value class Skill(private val skill: String) {
    override fun toString(): String {
        return skill
    }
}
