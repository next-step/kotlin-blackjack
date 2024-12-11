package dsl

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)

class SkillsBuilder {
    private var softSkills: MutableList<String> = mutableListOf()
    private var hardSkills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}
