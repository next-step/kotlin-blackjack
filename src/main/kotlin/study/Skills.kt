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

    fun introduce(): String {
        return "I have soft skills: ${softSkills.joinToString { it.introduce() }}, hard skills: ${hardSkills.joinToString { it.introduce() }}"
    }
}

@JvmInline
value class Skill(private val skill: String) {
    fun introduce(): String {
        return skill
    }
}
