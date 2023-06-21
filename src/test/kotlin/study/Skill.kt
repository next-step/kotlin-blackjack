package study

sealed interface Skill {
    val value: String
}

@JvmInline
value class SoftSkill(override val value: String) : Skill

@JvmInline
value class HardSkill(override val value: String) : Skill
