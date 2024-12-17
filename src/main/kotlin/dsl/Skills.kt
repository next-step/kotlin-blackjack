package dsl


data class Skills(private val skills: List<Skill> = emptyList()) {
    fun getSkills(type: SkillType): List<String> {
        return skills.filter { it.type == type }.map { it.description }
    }
}
