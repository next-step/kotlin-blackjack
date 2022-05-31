package step1.skill

class Skills(skills: List<Skill> = listOf()) {
    private var _skills: MutableList<Skill> = skills.toMutableList()
    val list: List<Skill>
        get() = _skills.toList()

    fun add(skill: Skill) {
        _skills.add(skill)
    }
}
