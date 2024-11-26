package study.domain

data class Skill(
    val softSkills: List<SoftSkill>,
    val hardSkills: List<HardSkill>,
) {
    class SkillsBuilder {
        private val softSkills = mutableListOf<SoftSkill>()
        private val hardSkills = mutableListOf<HardSkill>()

        fun soft(value: String) {
            softSkills.add(SoftSkill(value))
        }

        fun hard(value: String) {
            hardSkills.add(HardSkill(value))
        }

        fun build(): Skill {
            return Skill(softSkills, hardSkills)
        }
    }
}
