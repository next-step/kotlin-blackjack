package dsl

@JvmInline
value class SoftSkill(override val value: String) : Skill {
    val level: String
        get() = "soft"
}
