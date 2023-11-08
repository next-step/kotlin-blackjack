package study

data class Skills(
    val hardSkills: List<Skill.Hard> = emptyList(),
    val softSkills: List<Skill.Soft> = emptyList(),
)
