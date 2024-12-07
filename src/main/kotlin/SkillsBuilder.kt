import model.Skill
import model.Skills

@PersonDsl
class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill(Skill.Type.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(Skill.Type.HARD, value))
    }

    fun build(): Skills = Skills(skills)
}
