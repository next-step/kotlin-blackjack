package dsl

class Skills(
    skills: List<Skill> = emptyList()
) {
    private val _skills = skills.toMutableList()
    private val skills
        get() = _skills.toList()

    fun add(skill: Skill) {
        _skills.add(skill)
    }
}
