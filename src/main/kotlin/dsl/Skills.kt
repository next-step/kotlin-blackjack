package dsl

sealed class Skill(val description: String)
class Soft(description: String) : Skill(description)
class Hard(description: String) : Skill(description)

@JvmInline
value class Skills(val values: List<Skill>)

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()
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
