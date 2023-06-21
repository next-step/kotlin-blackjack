package dsl

sealed class Skill(open val detail: String) {

    data class Soft(override val detail: String) : Skill(detail)

    data class Hard(override val detail: String) : Skill(detail)
}

data class Skills(val values: List<Skill> = listOf()) {
    val size: Int get() = values.size

    operator fun get(index: Int): Skill = values[index]
}

class SkillsBuilder {
    private var skills = mutableListOf<Skill>()

    fun soft(detail: String) {
        skills.add(Skill.Soft(detail))
    }

    fun hard(detail: String) {
        skills.add(Skill.Hard(detail))
    }

    fun build(): Skills = Skills(skills)
}
