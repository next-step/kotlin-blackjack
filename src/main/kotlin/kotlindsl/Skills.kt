package kotlindsl

import kotlindsl.util.AddOnlyMutableList

sealed class Skill {
    data class Soft(val value: String) : Skill()
    data class Hard(val value: String) : Skill()
}

class Skills : AddOnlyMutableList<Skill>() {
    fun soft(value: String) = this.add(Skill.Soft(value))
    fun hard(value: String) = this.add(Skill.Hard(value))
}
