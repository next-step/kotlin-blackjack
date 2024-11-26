package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.builder.Language
import study.builder.Person
import study.builder.PersonBuilder
import study.builder.Skill
import study.builder.Skill.Companion.SkillType

//introduce {
//    name("권은성")
//    company("회사")
//    skills {
//        soft("A passion for problem solving")
//        soft("Good communication skills")
//        hard("Kotlin")
//    }
//    languages {
//        "Korean" level 5
//        "English" level 3
//    }
//}

class DslTest {
    @ValueSource(strings = ["권은성", "권"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person = introduce {
            name("권은성")
            company("회사")
        }
        person.company shouldBe "회사"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("권은성")
            company("회사")
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

        val expectedSkills = listOf(
            Skill("A passion for problem solving", SkillType.SOFT),
            Skill("Good communication skills", SkillType.SOFT),
            Skill("Kotlin", SkillType.HARD)
        )
        person.skills shouldBe  expectedSkills
    }

    @Test
    fun languages() {
        val person = introduce {
            name("권은성")
            company("회사")
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

        val expectedLanguage = listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
        person.languages shouldBe  expectedLanguage
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}



