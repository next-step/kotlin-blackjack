package step1

class Skills(private val skills: MutableList<Skill> = mutableListOf()) : List<Skill> by skills {
    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun soft(name: String) {
        skills.add(Soft(name))
    }
}
