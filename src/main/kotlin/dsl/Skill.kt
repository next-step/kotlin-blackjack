package dsl

data class Skills(
    val value: List<Skill>
) {
    companion object {
        fun empty(): Skills = Skills(emptyList())
    }
}

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(detail: String) {
        skills.add(Skill(SkillType.SOFT, detail))
    }

    fun hard(detail: String) {
        skills.add(Skill(SkillType.HARD, detail))
    }

    fun build() = skills.toList().let(::Skills)
}

data class Skill(
    val type: SkillType,
    val detail: String,
)

enum class SkillType {
    SOFT, HARD
}
