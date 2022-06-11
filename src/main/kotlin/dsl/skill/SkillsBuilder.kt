package dsl.skill

class SkillsBuilder {
    private val soft: ArrayList<Skill> = ArrayList()
    private val hard: ArrayList<Skill> = ArrayList()

    fun soft(skill: String) {
        soft.add(Soft(skill))
    }

    fun hard(skill: String) {
        hard.add(Hard(skill))
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}
