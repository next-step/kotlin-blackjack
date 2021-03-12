package study

sealed class Skill

data class Hard(val value: String) : Skill()

data class Soft(val value: String) : Skill()
