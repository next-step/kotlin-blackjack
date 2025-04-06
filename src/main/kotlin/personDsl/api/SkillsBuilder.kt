package personDsl.api

interface SkillsBuilder {
    fun soft(softSkill: String)

    fun hard(hardSkill: String)
}
