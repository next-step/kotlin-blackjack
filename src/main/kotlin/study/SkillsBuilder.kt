package study

class SkillsBuilder {
    private val hardSkills = mutableListOf<String>()
    private val softSkills = mutableListOf<String>()
    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun build() = Skills(hardSkills, softSkills)
}