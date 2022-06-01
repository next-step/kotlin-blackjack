package study

enum class SkillType {
    SOFT,
    HARD,
}

data class Skill(val type: SkillType, val description: String) {

    companion object {

        class SkillBuilder {
            private var skills = mutableListOf<Skill>()

            fun soft(value: String) {
                skills.add(Skill(SkillType.SOFT, value))
            }

            fun hard(value: String) {
                skills.add(Skill(SkillType.HARD, value))
            }

            fun build(): List<Skill> = skills
        }
    }
}
