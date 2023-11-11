package dsl

class SkillBuilder {

    private var softSkills: MutableList<Skill> = mutableListOf()
    private var hardSkills: MutableList<Skill> = mutableListOf()

    fun soft(skill: String) {
        Skill(skill).also { softSkills.add(it) }
    }

    fun hard(skill: String) {
        Skill(skill).also { hardSkills.add(it) }
    }

    fun build(): Skills = Skills(
        soft = softSkills,
        hard = hardSkills,
    )
}
