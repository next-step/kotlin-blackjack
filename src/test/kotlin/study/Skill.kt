package study

sealed class Skill(open val name: String)

data class Hard(override val name: String) : Skill(name)
data class Soft(override val name: String) : Skill(name)
