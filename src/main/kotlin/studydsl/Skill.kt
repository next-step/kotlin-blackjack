package studydsl

sealed class Skill {
    abstract val name: String

    data class HardSkill(override val name: String) : Skill()

    data class SoftSkill(override val name: String) : Skill()
}
