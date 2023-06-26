package introduce

data class Skill(val name: String)
class SkillBuilder {

    private val skills: MutableList<Skill> = mutableListOf()
    fun soft(value: String) {
        skills.add(Skill(value))
    }
    fun hard(value: String) {
        skills.add(Skill(value))
    }
    fun build(): List<Skill> {
        return skills
    }
}
