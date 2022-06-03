package com.nextstep.jngcii.dsl

sealed class Skill {

    data class Soft(val description: String) : Skill()

    object Kotlin : Skill()
}

class SkillBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Skill.Soft(value))
    }

    fun hard(skill: Skill) {
        skills.add(skill)
    }

    fun build() = skills
}
