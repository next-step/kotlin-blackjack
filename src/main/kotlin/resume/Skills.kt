package resume

sealed interface Skill {
    val name: String

    data class Hard(override val name: String) : Skill
    data class Soft(override val name: String) : Skill
}

data class Skills(private val skill: MutableList<Skill> = mutableListOf()) : MutableList<Skill> by skill {

    fun soft(name: String) {
        skill.add(Skill.Soft(name))
    }

    fun hard(name: String) {
        skill.add(Skill.Hard(name))
    }
}
