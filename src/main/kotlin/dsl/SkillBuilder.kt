package dsl

class SkillBuilder {

    private var softSkills: MutableList<String> = mutableListOf()
    private var hardSkills: MutableList<String> = mutableListOf()

    fun soft(skill: String) {
        softSkills.add(skill)
    }

    fun hard(skill: String) {
        hardSkills.add(skill)
    }

    fun build(): Skills = Skills(
        soft = softSkills,
        hard = hardSkills,
    )
}
