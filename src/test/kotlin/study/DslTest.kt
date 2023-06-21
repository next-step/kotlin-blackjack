package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.domain.Language
import study.domain.Person
import study.domain.Skill
import study.domain.introduce

class DslTest {

    @ValueSource(strings = ["홍길동", "제이슨"])
    @ParameterizedTest
    fun introduce() {
        val person: Person = introduce {
            this.name("홍길동")
        }
        person.name shouldBe "홍길동"
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
                soft("커뮤니케이션")
                soft("팀워크")
                hard("Kotlin")
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills?.skills shouldBe listOf(
            Skill("soft", "커뮤니케이션"),
            Skill("soft", "팀워크"), Skill("hard", "Kotlin")
        )
    }

    @Test
    fun language() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("커뮤니케이션")
                soft("팀워크")
                hard("Kotlin")
            }
            languages {
                "Kotlin" level 5
                "Java" level 3
                "Javascript" level 1
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills?.skills shouldBe listOf(
            Skill("soft", "커뮤니케이션"),
            Skill("soft", "팀워크"), Skill("hard", "Kotlin")
        )
        person.languages?.languages shouldBe listOf(
            Language("Kotlin", 5),
            Language("Java", 3),
            Language("Javascript", 1)
        )
    }
}






