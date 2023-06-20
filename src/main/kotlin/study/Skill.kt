package study

sealed class Skill {

    data class Soft(val name: String) : Skill()
    data class Hard(val name: String) : Skill()

    companion object {
        fun soft(name: String): Skill = Soft(name)
        fun hard(name: String): Skill = Hard(name)
    }
}
