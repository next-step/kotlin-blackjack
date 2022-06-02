package kotlindsl

import kotlindsl.util.AddOnlyMutableList

sealed interface Skill {

    open val value: String

    data class Soft(override val value: String) : Skill
    data class Hard(override val value: String) : Skill
}

class Skills : AddOnlyMutableList<Skill>() {
    fun soft(value: String) = this.add(Skill.Soft(value))
    fun hard(value: String) = this.add(Skill.Hard(value))
}
