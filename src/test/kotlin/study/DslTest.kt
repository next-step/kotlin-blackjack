package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import study.dsl.introduce
import study.dsl.skills

class DslTest {
    @Test
    fun `skill DSL 적용 테스트`() {
        val skills = skills {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }

        skills.size shouldBe 3
        skills[0].description shouldBe "A passion for problem solving"
    }

    @Test
    fun languageTest() {
        val person = introduce {
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
        person.languages.size shouldBe 2
        person.languages[0].name shouldBe "Korean"
        person.languages[0].level shouldBe 5
    }
}
