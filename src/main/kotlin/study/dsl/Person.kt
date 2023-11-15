package study.dsl

import study.builder.LangaugeBuilder
import study.builder.PersonBuilder
import study.builder.SkillsBuilder
import study.dto.Language
import study.dto.Person
import study.dto.Skill

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

fun languages(block: LangaugeBuilder.() -> Unit): List<Language> {
    return LangaugeBuilder().apply(block).build()
}

fun skills(block: SkillsBuilder.() -> Unit): List<Skill> {
    return SkillsBuilder().apply(block).build()
}
