class SkillBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(value: String) {
        this.skills.add(Soft(value))
    }

    fun hard(value: String) {
        this.skills.add(Hard(value))
    }

    fun build(): Skills {
        return Skills(list = skills.toList())
    }
}
