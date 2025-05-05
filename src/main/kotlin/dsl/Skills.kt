package dsl

@PersonDsl
data class Skills(
    val values: MutableList<Skill> = mutableListOf(),
) {
    fun soft(skill: String) {
        this.values.add(Skill.Soft(skill))
    }

    fun hard(skill: String) {
        this.values.add(Skill.Hard(skill))
    }
}

sealed class Skill(
    val value: String,
) {
    data class Soft(val skill: String) : Skill(skill)

    data class Hard(val skill: String) : Skill(skill)
}
