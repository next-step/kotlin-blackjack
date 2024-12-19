package dsl

@JvmInline
value class Skills(private val skillList: List<Skill> = emptyList()) : List<Skill> by skillList {
    fun getSkills(type: SkillType): List<String> {
        return skillList.filter { it.type == type }.map { it.description }
    }
}
