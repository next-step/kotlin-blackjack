package study

data class Skills(val skills: List<Skill>) {

    operator fun contains(skill: Skill): Boolean = skills.contains(skill)
}

class SkillsBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
