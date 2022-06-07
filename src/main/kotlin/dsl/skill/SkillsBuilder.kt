package dsl.skill

class SkillsBuilder {
    private val soft = mutableListOf<SoftSkill>()
    private val hard = mutableListOf<HardSkill>()

    fun soft(title: String) {
        soft.add(SoftSkill(title))
    }

    fun hard(title: String) {
        hard.add(HardSkill(title))
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}
