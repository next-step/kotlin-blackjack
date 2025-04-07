package dsl

data class Skills(
    val softSkills: MutableList<String> = mutableListOf(),
    val hardSkills: MutableList<String> = mutableListOf(),
) {
    fun soft(skill: String) {
        this.softSkills.add(skill)
    }

    fun hard(skill: String) {
        this.hardSkills.add(skill)
    }
}
