package study

class Skills {

    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun toList(): List<Skill> = skills
}
