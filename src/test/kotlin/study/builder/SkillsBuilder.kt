package study.builder

class SkillsBuilder {
    private val skills = mutableListOf<String>()

    fun soft(value: String) {
        skills.add(value)
    }

    fun hard(value: String) {
        skills.add(value)
    }

    fun build(): List<String> {
        return skills
    }
}