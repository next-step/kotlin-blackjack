package dsl

@JvmInline
value class Skills(private val values: List<Skill> = emptyList()) : List<Skill> by values

@DeveloperMarker
class SkillsBuilder : Builder<Skills> {
    private var skills: MutableList<Skill> = mutableListOf()

    infix fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    infix fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    override fun build(): Skills = Skills(skills.toList())
}
