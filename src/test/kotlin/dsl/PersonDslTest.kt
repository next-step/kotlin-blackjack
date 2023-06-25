package dsl

import dsl.language.Language
import dsl.person.Person
import dsl.skill.Skill
import dsl.skill.SkillType
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonDslTest {
    @ValueSource(strings = ["김현준", "홍길동"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("김현준")
            company("카카오")
        }
        person.name shouldBe "김현준"
        person.company shouldBe "카카오"
    }

    @Test
    fun skillsTest() {
        val person = introduce {
            name("김현준")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                hard("kotlin")
            }
        }
        person.skills shouldContain Skill(SkillType.SOFT, "A passion for problem solving")
        person.skills shouldContain Skill(SkillType.HARD, "kotlin")
    }

    @Test
    fun languagesTest() {
        val person = introduce {
            name("김현준")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.languages shouldContain Language(name = "Korean", level = 5)
        person.languages shouldContain Language(name = "English", level = 3)
    }
}
