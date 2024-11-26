package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "홍", "이민수", "테스터"])
    fun nameTest(name: String) {
        val person =
            introduce {
                name(name)
                company("활빈당")
            }
        println(person)

        person.name shouldBe name
    }

    @ParameterizedTest
    @ValueSource(strings = ["활빈당", "홍", "이민수", "테스터"])
    fun companyTest(company: String) {
        val person =
            introduce {
                company(company)
            }
        println(person)

        person.company shouldBe company
    }

    @Test
    fun skillsTest() {
        val person =
            introduce {
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        val skills =
            person.skills.skills.map {
                when (it) {
                    is Skill.SoftSkill -> "soft: ${it.description}"
                    is Skill.HardSkill -> "hard: ${it.description}"
                }
            }
        println(person)

        val result =
            listOf(
                "soft: A passion for problem solving",
                "soft: Good communication skills",
                "hard: Kotlin",
            )

        skills[0] shouldBe result[0]
        skills[1] shouldBe result[1]
        skills[2] shouldBe result[2]
    }

    @Test
    fun languagesTest() {
        val person =
            introduce {
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        println(person)

        val languages = person.languages.languages
        languages[0] shouldBe Language("Korean", 5)
        languages[1] shouldBe Language("English", 3)
    }

    @Test
    fun introduceTest() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        println(person)

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"

        val skills =
            person.skills.skills.map {
                when (it) {
                    is Skill.SoftSkill -> "soft: ${it.description}"
                    is Skill.HardSkill -> "hard: ${it.description}"
                }
            }
        val result =
            listOf(
                "soft: A passion for problem solving",
                "soft: Good communication skills",
                "hard: Kotlin",
            )
        skills.forEachIndexed { index, skill ->
            skill shouldBe result[index]
        }

        val languages = person.languages.languages
        languages[0] shouldBe Language("Korean", 5)
        languages[1] shouldBe Language("English", 3)
    }
}
