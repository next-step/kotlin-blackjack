package kotlindsl

import kotlindsl.Skill.Hard
import kotlindsl.Skill.Soft

sealed interface Skill {
    data class Soft(val softSkills: List<String>) : Skill
    data class Hard(val hardSkills: List<String>) : Skill
}

data class Skills(val soft: Soft, val hard: Hard)

class SkillBuilder {
    private val _soft = mutableListOf<String>()

    private val _hard = mutableListOf<String>()

    private val soft: List<String>
        get() = _soft.toList()

    private val hard: List<String>
        get() = _hard.toList()

    fun soft(value: String) = _soft.add(value)

    fun hard(value: String) = _hard.add(value)

    fun toBuilder(): Skills {
        return Skills(Soft(soft), Hard(hard))
    }
}
