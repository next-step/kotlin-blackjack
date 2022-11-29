package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import study.skill.Hard
import study.skill.Soft

class DslTest {

    @Test
    fun introduce() {
        val person: Person = Introduce.introduce {
            name("홍길동")
        }

        person.name shouldBe "홍길동"
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person: Person = Introduce.introduce {
            name("홍길동")
            company("활빈당")
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person: Person = Introduce.introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person: Person = Introduce.introduce {
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

        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills shouldContainExactlyInAnyOrder listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
        person.languages shouldContainExactly mapOf(
            "Korean" to 5,
            "English" to 3
        )
    }
}
