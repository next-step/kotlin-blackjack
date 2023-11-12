package dsl

sealed interface Skill {
    val name: String
}

data class SoftSkill(
    override val name: String,
) : Skill

data class HardSkill(
    override val name: String,
) : Skill
