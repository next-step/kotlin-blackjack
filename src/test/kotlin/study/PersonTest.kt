package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({
    "person 생성 테스트" {
        val person = introduce {
            name("홍길동")
            company("홍길동 회사")
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
        person.company shouldBe "홍길동 회사"
        person.skills shouldContainAll listOf(
            Skill.soft("A passion for problem solving"),
            Skill.soft("Good communication skills"),
            Skill.hard("Kotlin")
        )
        person.languages shouldContainAll listOf(
            Language.korean(5),
            Language.english(3)
        )
    }
})
