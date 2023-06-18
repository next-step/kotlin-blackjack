package dsl

@JvmInline
value class HardSkill(override val value: String) : Skill {
    val level: String
        get() = "hard"
}
