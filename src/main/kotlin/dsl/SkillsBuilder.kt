package dsl

class SkillsBuilder {
    val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(Soft(description))
    }

    fun hard(description: String) {
        skills.add(Hard(description))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
