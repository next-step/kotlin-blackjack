package resume

sealed class Skill(
    open val name: String,
)

data class HardSkill(
    override val name: String,
) : Skill(name)

data class SoftSkill(
    override val name: String,
) : Skill(name)
