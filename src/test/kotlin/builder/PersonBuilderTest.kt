package builder

import dsl.builder.PersonBuilder
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PersonBuilderTest : BehaviorSpec({

    given("정상적인 정보가 주어졌다") {
        `when`("해당 정보로 사용자를 생성하면") {
            then("정상적으로 생성된다") {
                val person = PersonBuilder().apply {
                    name("사용자")
                    company("회사명")
                    skills {
                        soft("A passion for problem solving")
                        hard("Kotlin")
                    }
                    languages {
                        "Korean" level 5
                        "English" level 1
                    }
                }.build()

                person.name shouldBe "사용자"
                person.company shouldBe "회사명"
                person.skills.size shouldBe 2
                person.languages.size shouldBe 2
            }
        }
    }
})
