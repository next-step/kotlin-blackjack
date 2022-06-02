package com.nextstep.jngcii.dsl

data class Skills(
    val softs: List<SoftSkill>,
    val hards: List<HardSkill>
)

data class SoftSkill(val description: String)

enum class HardSkill {
    KOTLIN;

    companion object {
        fun of(value: String): HardSkill? {
            return values().find {
                it.name.lowercase() == value.lowercase()
            }
        }
    }
}

class SkillBuilder {
    private val softSkills = mutableListOf<SoftSkill>()
    private val hardSkills = mutableListOf<HardSkill>()

    fun soft(value: String) {
        softSkills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        HardSkill.of(value)?.let {
            hardSkills.add(it)
        }
    }

    fun build() = Skills(
        softSkills.toList(),
        hardSkills.toList()
    )
}
