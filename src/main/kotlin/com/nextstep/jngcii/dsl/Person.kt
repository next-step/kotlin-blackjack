package com.nextstep.jngcii.dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String,
    val softSkills: List<SoftSkill>,
    val hardSkills: List<HardSkill>
)

class PersonBuilder {
    var name: String = ""
    var company: String? = null
    var softSkills: List<SoftSkill> = emptyList()
    var hardSkills: List<HardSkill> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        val skills = SkillBuilder().apply(block).build()
        softSkills = skills.softs
        hardSkills = skills.hards
    }

    fun build() = Person(
        name = name,
        company = company ?: "무직",
        softSkills = softSkills,
        hardSkills = hardSkills
    )
}
