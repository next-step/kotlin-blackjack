package dsl

sealed interface Skill {
    data class Soft(val skill: String) : Skill
    data class Hard(val skill: String) : Skill
}
