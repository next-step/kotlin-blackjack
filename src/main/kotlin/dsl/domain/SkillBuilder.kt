package dsl.domain

/**
 * Created by Jaesungchi on 2022.06.03..
 */
class SkillBuilder {
    private val skills: Skills = Skills()

    fun soft(value: String) {
        skills.addSoft(Skill(value))
    }

    fun hard(value: String) {
        skills.addHard(Skill(value))
    }

    fun build(): Skills {
        return skills
    }
}
