package dsl

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
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skillsTest() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
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
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
    }
}
