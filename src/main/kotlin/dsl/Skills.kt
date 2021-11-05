package dsl

class Skills {
    private val _skills: MutableList<Skill> = mutableListOf()
    val items: List<Skill>
        get() = _skills.toList()

    fun hard(name: String) {
        _skills.add(Hard(name))
    }

    fun soft(name: String) {
        _skills.add(Soft(name))
    }
}
