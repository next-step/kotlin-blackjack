package resume

sealed interface Skill {
    val name: String

    data class Hard(override val name: String) : Skill
    data class Soft(override val name: String) : Skill
}
