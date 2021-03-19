package study

sealed class Skill

data class Hard(private val name: String) : Skill()

data class Soft(private val name: String) : Skill()
