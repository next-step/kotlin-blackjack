package dsl.skill

class SkillsBuilder {
    private val soft: ArrayList<Skill> = ArrayList()
    private val hard: ArrayList<Skill> = ArrayList()

    fun soft(skill: String) {
        soft.add(Skill(skill))
    }

    fun hard(value: String) {
        hard.add(Skill(value))
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}