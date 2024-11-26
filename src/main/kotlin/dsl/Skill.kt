package dsl

sealed interface Skill {
    data class SoftSkill(val description: String) : Skill

    data class HardSkill(val description: String) : Skill
}
