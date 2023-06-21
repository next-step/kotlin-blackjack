package dsl.builder

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class SkillsBuilderTest : BehaviorSpec({

    given("정상적인 정보가 주어졌다") {
        `when`("해당정보로 Skills를 생성하면") {
            then("정상적으로 생성된다") {
                val skills = SkillsBuilder {
                    soft("테스트 작성능력")
                    hard("개발 능력")
                }.build()
                skills.size shouldBe 2
            }
        }
    }
})
