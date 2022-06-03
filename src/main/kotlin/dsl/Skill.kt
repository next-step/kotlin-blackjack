package dsl

class Skills(
    soft: List<Skill> = emptyList(),
    hard: List<Skill> = emptyList()
) {
    private val _soft = soft.toMutableList()
    val soft: List<Skill> get() = _soft.toList()

    private val _hard = hard.toMutableList()
    val hard: List<Skill> get() = _hard.toList()

    fun soft(value: Skill) {
        _soft.add(value)
    }

    fun hard(value: Skill) {
        _hard.add(value)
    }
}

data class Skill(val title: String)
