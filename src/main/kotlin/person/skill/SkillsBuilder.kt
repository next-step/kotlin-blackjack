package person.skill

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(name: String) {
        skills.add(Skill(SkillType.SOFT, name))
    }

    fun hard(name: String) {
        skills.add(Skill(SkillType.HARD, name))
    }

    fun build(): List<Skill> {
        return skills
    }

}
