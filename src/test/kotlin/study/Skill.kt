package study

sealed class Skill(val type: String, val content: String) {
    class Hard(content: String) : Skill("hard", content)
    class Soft(content: String) : Skill("soft", content)
}

data class Skills(val skills: List<Skill>) : List<Skill> by skills {
    companion object {
        fun empty() = Skills(emptyList())
    }
}

class SkillsDsl {

    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(content: String) {
        skills.add(Skill.Soft(content))
    }

    fun hard(content: String) {
        skills.add(Skill.Hard(content))
    }

    fun toSkills(): Skills {
        return Skills(skills)
    }
}
