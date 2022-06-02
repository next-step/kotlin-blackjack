package camp.nextstep.study

data class Skills(val skills: List<Skill>) {

    fun getHardSkills() = skills.filter { it.type == Skill.SkillType.HARD }.toSkills()

    fun getSoftSkills() = skills.filter { it.type == Skill.SkillType.SOFT }.toSkills()

    fun size() = skills.size

    fun contains(skill: Skill) = skills.contains(skill)
}
