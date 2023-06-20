package study

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

/**
 * introduce {
 *   name("한용희")
 *   company("용희컴퍼니")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 1
 *   }
 * }
 */
@DisplayName("코틀린 DSL 학습")
class DslTest : StringSpec({

    "회사 안다니는 사람 생성" {
        listOf("한용희", "홍길동")
            .forAll {
                // given & when
                val person: Person = introduce {
                    name(it)
                }
                // then
                person.name shouldBe it
                person.company.shouldBeNull()
            }
    }

    "회사 다니고 있는 사람 생성" {
        // given & when
        val person: Person = introduce {
            name("한용희")
            company("용희컴퍼니")
        }
        // then
        person.name shouldBe "한용희"
        person.company shouldBe "용희컴퍼니"
    }

    "스킬 정보 추가된 사람 생성" {
        // given & when
        val person: Person = introduce {
            name("한용희")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        // when & then
        assertSoftly(person) {
            skills.soft shouldContainAll listOf(
                "A passion for problem solving",
                "Good communication skills",
            )
            skills.hard shouldContainAll listOf(
                "Kotlin"
            )
        }
    }

    "언어 정보 추가된 사람 생성" {
        // given & when
        val person: Person = introduce {
            name("한용희")
            languages {
                "Korean" level 5
                "English" level 1
            }
        }

        // then
        assertSoftly(person) {
            languages["Korean"] shouldBe 5
            languages["English"] shouldBe 1
        }
    }

    "모든 정보에 포함하는 사람 생성" {
        // given & when
        val person: Person = introduce {
            name("한용희")
            company("용희컴퍼니")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 1
            }
        }
        // then
        assertSoftly(person) {
            name shouldBe "한용희"
            company shouldBe "용희컴퍼니"
            skills.soft shouldContainAll listOf(
                "A passion for problem solving",
                "Good communication skills",
            )
            skills.hard shouldContainAll listOf(
                "Kotlin"
            )
            languages["Korean"] shouldBe 5
            languages["English"] shouldBe 1
        }
    }
})

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Languages,
)

class Skills(
    soft: List<String>,
    hard: List<String>,
) {
    val soft: List<String> = soft.toList()
    val hard: List<String> = hard.toList()
}

class Languages(
    languages: Map<String, Int>,
) : Map<String, Int> by languages.toMap()

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: SkillsBuilder = SkillsBuilder()
    private val languages: LanguagesBuilder = LanguagesBuilder()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skills.build(), languages.build())
    }
}

class SkillsBuilder {
    private val softSkill: MutableList<String> = mutableListOf()
    private val hardSkill: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softSkill.add(value)
    }

    fun hard(value: String) {
        hardSkill.add(value)
    }

    fun build(): Skills {
        return Skills(softSkill, hardSkill)
    }
}

class LanguagesBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
