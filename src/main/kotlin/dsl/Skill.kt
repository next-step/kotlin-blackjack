package dsl

class Skill {
    private val hardSkills: MutableList<String> = mutableListOf()
    private val softSkills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun getSoftSkillSize(): Int {
        return softSkills.size
    }

    fun getHardSkillSize(): Int {
        return hardSkills.size
    }
}
