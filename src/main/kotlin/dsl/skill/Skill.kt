package dsl.skill

sealed class Skill
data class Hard(val skill: String) : Skill()
data class Soft(val skill: String) : Skill()
