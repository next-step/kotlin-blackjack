package dsl

sealed interface Skill {
    val name: String

    data class Soft(override val name: String) : Skill

    data class Hard(override val name: String) : Skill
}

class Skills(values: List<Skill> = listOf()) {
    private val _values = values.toMutableList()
    val values: List<Skill> get() = _values.toList()

    fun soft(name: String) {
        _values.add(Skill.Soft(name))
    }

    fun hard(name: String) {
        _values.add(Skill.Hard(name))
    }
}

class SkillsBuilder {
    private val skills: Skills = Skills()

    fun soft(name: String) {
        skills.soft(name)
    }

    fun hard(name: String) {
        skills.hard(name)
    }

    fun build(): Skills {
        return skills
    }
}
