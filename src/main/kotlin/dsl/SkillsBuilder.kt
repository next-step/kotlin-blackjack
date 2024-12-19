package dsl

@SkillsDsl
class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun skill(type: SkillType, description: String) {
        skills.add(Skill(type, description))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
