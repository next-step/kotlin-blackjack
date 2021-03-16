package study

class Skills {
    private var _values: MutableList<Skill> = mutableListOf()
    val values: List<Skill>
        get() = _values.toMutableList()

    fun hard(name: String) {
        _values.add(Hard(name))
    }

    fun soft(name: String) {
        _values.add(Soft(name))
    }
}

sealed class Skill

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()
