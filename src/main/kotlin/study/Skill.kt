package study

data class Skill(val desc: String, val rank: Rank)

sealed class Rank {
    object SOFT : Rank()
    object HARD : Rank()
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(desc: String) {
        skills.add(Skill(desc, Rank.SOFT))
    }

    fun hard(desc: String) {
        skills.add(Skill(desc, Rank.HARD))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
