package kotlindsl

import kotlindsl.util.AddOnlyMutableList

data class Skill(val skill: String, val type: SkillType)
class Skills : AddOnlyMutableList<Skill>() {
    fun soft(soft: String) = this.add(Skill(soft, SkillType.SOFT))
    fun hard(hard: String) = this.add(Skill(hard, SkillType.HARD))
}

enum class SkillType(val displayName: String) { SOFT("soft"), HARD("hard") }
