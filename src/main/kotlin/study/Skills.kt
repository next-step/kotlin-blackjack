package study

class Skills {
    private var _skills = mutableListOf<Skill>()

    var skills = emptyList<Skill>()
        get() = _skills.toList()

    fun add(skill: Skill) {
        _skills.add(skill)
    }
}
