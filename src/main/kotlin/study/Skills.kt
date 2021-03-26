package study

class Skills {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun getSkills(): List<Skill> = skills.toList()
}

sealed class Skill

data class Hard(val skill: String) : Skill()
data class Soft(val skill: String) : Skill()
