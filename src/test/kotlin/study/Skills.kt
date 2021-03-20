package study

class Skills(
    val values: List<Skill>

)

class SkillsBuilder {
    private val values: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        values.add(Hard(name))
    }

    fun soft(name: String) {
        values.add(Soft(name))
    }

    fun build(): Skills {
        return Skills(values)
    }
}

sealed class Skill

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()
