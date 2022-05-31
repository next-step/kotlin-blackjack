package resume

data class Skills(private val skill: MutableList<Skill> = mutableListOf()) : MutableList<Skill> by skill {

    fun soft(name: String) {
        skill.add(Skill.Soft(name))
    }

    fun hard(name: String) {
        skill.add(Skill.Hard(name))
    }
}
