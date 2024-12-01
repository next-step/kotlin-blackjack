package blackjack.step2.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("Player를 생성할때 이름을 입력하면 이름이 저장된다.") {
        // given
        val givenName = "dongyeon"

        // when
        val player = Player(givenName)

        // then
        player.name shouldBe givenName
    }

    test("Player를 생성할때 빈 이름을 입력하면 예외가 발생한다.") {
        // given
        val givenName = ""

        // when & then
        shouldThrow<IllegalArgumentException> {
            Player(givenName)
        }
    }
})
