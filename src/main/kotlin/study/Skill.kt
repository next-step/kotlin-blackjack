package study

sealed class Skill {
    class Hard(val name: String) : Skill()
    class Soft(val name: String) : Skill()
}