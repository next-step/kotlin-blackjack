package builder

import dsl.builder.LanguagesBuilder
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LanguagesBuilderTest : BehaviorSpec({

    given("정상적인 정보가 주어졌다") {
        `when`("해당 정보로 Languages를 생성하면") {
            then("정상적으로 생성된다") {
                val languages = LanguagesBuilder {
                    "korean" level 1
                }.build()
                languages.size shouldBe 1
            }
        }
    }
})
