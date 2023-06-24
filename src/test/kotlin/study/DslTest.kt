package study

import dsl.Language
import dsl.Person
import dsl.PersonBuilder
import dsl.Skill
import dsl.SkillBuilder
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val value = "김정욱"
        val person: Person = introduce {
            name(value)
            company()
        }.build()

        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김정욱")
            company("카카오")
        }.build()

        person.name shouldBe "김정욱"
        person.company shouldBe "카카오"
    }

    @Test
    fun skills() {
        val softSkills = "성실함, 능동성, 열정".split(',').map { Skill.fromSoft(it.trim()) }
        val hardSkills = "spring webflux, kotlin, nextjs, typescript".split(',').map { Skill.fromHard(it.trim()) }

        val person: Person = introduce {
            name("김정욱")
            company("카카오")
            skills {
                softSkills.forEach { this.soft(it.name) }
                hardSkills.forEach { this.hard(it.name) }
            }
        }.build()

        person.name shouldBe "김정욱"
        person.company shouldBe "카카오"
        person.skillBuilder shouldBe SkillBuilder(listOf(*softSkills.toTypedArray(), *hardSkills.toTypedArray()))
    }

    @Test
    fun languages() {
        val name = "김정욱"
        val company = "카카오"
        val softSkills = "성실함, 능동성, 열정".split(',').map { Skill.fromSoft(it.trim()) }
        val hardSkills = "spring webflux, kotlin, nextjs, typescript".split(',').map { Skill.fromHard(it.trim()) }
        val languages = mapOf("Korean" to 5, "English" to 3)

        val person: Person = introduce {
            name("김정욱")
            company("카카오")
            skills {
                soft("성실함")
                soft("능동성")
                soft("열정")
                hard("spring webflux")
                hard("kotlin")
                hard("nextjs")
                hard("typescript")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }.build()

        person.name shouldBe name
        person.company shouldBe company
        person.skillBuilder.skills shouldBe listOf(*softSkills.toTypedArray(), *hardSkills.toTypedArray())
        person.languageBuilder.languages shouldBe languages.map { Language(it.key to it.value) }
    }
}

fun introduce(block: PersonBuilder.() -> Unit): PersonBuilder {
    return PersonBuilder().apply(block)
}
