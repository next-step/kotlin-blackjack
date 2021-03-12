package study

class Skills {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun toList(): List<Skill> {
        return skills
    }
}

sealed class Skill

data class Soft(val name: String) : Skill()
data class Hard(val name: String) : Skill()
