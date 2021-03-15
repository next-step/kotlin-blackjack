package study

class Skills {
    private val _skills: MutableList<Skill> = mutableListOf()

    val skills: List<Skill>
        get() {
            return _skills.toList()
        }

    fun soft(name: String) {
        this._skills.add(Skill.Soft(name))
    }

    fun hard(name: String) {
        this._skills.add(Skill.Hard(name))
    }
}
