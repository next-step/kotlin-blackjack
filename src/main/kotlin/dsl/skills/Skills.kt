package dsl.skills

class Skills(val skills: MutableList<Skill> = mutableListOf()) : List<Skill> by skills {
    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }
}
