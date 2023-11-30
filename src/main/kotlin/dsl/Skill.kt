package dsl

sealed interface Skill {
    val value: String

    data class Soft(
        override val value: String,
    ) : Skill
    data class Hard(
        override val value: String,
    ) : Skill
}
