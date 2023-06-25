package dsl.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class IntroduceTest : BehaviorSpec({

    Given("정상적인 정보가 주어졌다") {
        When("해당 정보로 소개를 생성하면") {
            Then("정상적으로 생성된다") {
                val introduce = introduce {
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
                }

                introduce.name shouldBe "사용자"
                introduce.company shouldBe "회사명"
                introduce.skills.size shouldBe 2
                introduce.languages.size shouldBe 2
            }
        }
    }
})
