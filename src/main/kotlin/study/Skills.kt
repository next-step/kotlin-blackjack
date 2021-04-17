package study

class Skills {
    private var _skills = mutableListOf<Skill>()

    var skills = emptyList<Skill>()
        get() = _skills.toList()

    fun soft(name: String) {
        _skills.add(Skill(name))
    }

    fun hard(name: String) {
        _skills.add(Skill(name))
    }
}
