package study.skill

class SkillsBuilder {

    private val soft: MutableList<Skill> = mutableListOf()
    private var hard: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        soft.add(Skill(value))
    }

    fun hard(value: String) {
        hard.add(Skill(value))
    }

    fun build(): Skills = Skills(soft, hard)
}
