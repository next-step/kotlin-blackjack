package study

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import step1.Person
import step1.Skill
import step1.introduce

class DslTest {

    @ValueSource(strings = ["홍길동", "김대현"])
    @ParameterizedTest
    fun introduce(name: String) {
        val person: Person = introduce {
            name(name)
        }
        person.name shouldBe name
        person.company shouldBe null
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
    fun skills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft(Skill("softSkill1"))
                soft(Skill("softSkill2"))
                hard(Skill("hardSkill1"))
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills shouldNotBe null
    }

    @Test
    fun languages() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft(Skill("softSkill1"))
                soft(Skill("softSkill2"))
                hard(Skill("hardSkill1"))
            }
            languages {
                "Korean" level 5
                "English" level 0
            }
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills shouldNotBe null
        person.languages shouldNotBe null
    }
}