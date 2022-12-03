package study

sealed class Skill(open val name: String)
data class SoftSkill(override val name: String) : Skill(name)
data class HardSkill(override val name: String) : Skill(name)