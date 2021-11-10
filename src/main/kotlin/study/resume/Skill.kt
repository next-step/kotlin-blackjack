package study.resume

sealed class Skill

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()
