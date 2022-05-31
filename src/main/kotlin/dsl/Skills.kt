package dsl

class Skills {
    private val _skills: MutableList<Skill> = mutableListOf()
    val skills
        get() = _skills.toList()

    fun soft(value: String) {
        _skills.add(Soft(value))
    }

    fun hard(value: String) {
        _skills.add(Hard(value))
    }
}

abstract class Skill(open val value: String)

data class Soft(override val value: String) : Skill(value)
data class Hard(override val value: String) : Skill(value)
