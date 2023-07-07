package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NameTest : FunSpec({

    context("init") {
        test("이름이 5자 초과하면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Name("123456") }
            exception.message shouldBe "플레이어 이름은 5자를 초과할 수 없습니다."
        }

        test("이름을 생성한다.") {
            val actual = Name("최진영")
            actual.value shouldBe "최진영"
        }
    }
})
