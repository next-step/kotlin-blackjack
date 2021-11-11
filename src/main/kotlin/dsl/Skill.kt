package dsl

sealed class Skill

data class Soft(val skill: String) : Skill()
data class Hard(val skill: String) : Skill()
