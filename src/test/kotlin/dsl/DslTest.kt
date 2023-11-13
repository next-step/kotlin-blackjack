package dsl

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class DslTest : ExpectSpec({

    expect("사용자 속성을 입력하면 객체를 생성한다.") {
        val newPerson = introduce {
            name("박재성")
            company("우아한형제들")
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

        newPerson.name shouldBe "박재성"
        newPerson.company shouldBe "우아한형제들"
        newPerson.skills.size shouldBe 3
        newPerson.languages.size shouldBe 2
    }

    expect("이름 속성을 입력하지 않으면 오류가 발생한다.") {
        shouldThrow<IllegalArgumentException> {
            introduce {
                company("우아한형제들")
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
