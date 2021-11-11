package dsl

data class Skills(private val _values: MutableSet<Skill> = mutableSetOf()) {
    val values: Set<Skill>
        get() = _values.toSet()

    fun soft(skill: String) = _values.add(Soft(skill))

    fun hard(skill: String) = _values.add(Hard(skill))
}
