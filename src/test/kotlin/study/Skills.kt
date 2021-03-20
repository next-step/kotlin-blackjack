package study

class Skills(
    val values: List<Skill>
)

class SkillsBuilder {
    private val values: MutableList<Skill> = mutableListOf()

    fun hard(nameLamda: () -> String) {
        values.add(Hard(nameLamda()))
    }

    fun soft(nameLamda: () -> String) {
        values.add(Soft(nameLamda()))
    }

    fun build(): Skills {
        return Skills(values)
    }
}

sealed class Skill

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()
