package dsl

sealed interface Skill {
    val name: String

    data class Soft(override val name: String) : Skill
    data class Hard(override val name: String) : Skill
}

