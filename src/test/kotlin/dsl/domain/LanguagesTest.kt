package dsl.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class LanguagesTest : BehaviorSpec({
    given("빈 언어 리스트가 있다") {
        val list = emptyList<Language>()
        `when`("해당 리스트로 Languages를 생성하면") {
            then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Languages(list) }
            }
        }
    }

    given("정상적인 능력 리스트가 있다") {
        val list = listOf(Language("korean", 1))
        `when`("해당 리스트로 Languages를 생성하면") {
            then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Languages(list) }
            }
        }
    }
})
