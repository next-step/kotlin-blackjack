package study

sealed class Skill {
    data class Soft(val name:String): Skill()
    data class Hard(val name:String): Skill()
}
