package study

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill("soft", description))
    }

    fun hard(description: String) {
        skills.add(Skill("hard", description))
    }

    fun build(): Skills {
        return Skills(this.skills)
    }
}
