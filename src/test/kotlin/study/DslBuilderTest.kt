package study

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class DslBuilderTest : StringSpec({
    "person을 생성한다." {
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

    "person 생성 시 지정하지 않으면 예외가 발생한다." {
        shouldThrowAny {
            introduce {
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
        }
    }

})
