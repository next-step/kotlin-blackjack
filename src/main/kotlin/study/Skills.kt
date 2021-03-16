package study

class Skills {
    private val _skills: MutableList<Skill> = mutableListOf()
    private val skills: List<Skill>
        get() = _skills

    fun hard(name: String) {
        _skills.add(Hard(name))
    }

    fun soft(name: String) {
        _skills.add(Soft(name))
    }

    fun toList(): List<Skill> {
        return skills
    }
}
