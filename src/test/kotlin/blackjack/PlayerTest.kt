package blackjack

import blackjack.gamestate.InitialHand
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class PlayerTest : FunSpec({

    context("init") {
        test("이름이 5자 초과 시 예외가 한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Player("123456") }
            exception.message shouldBe "플레이어 이름은 5자를 초과할 수 없다."
        }

        test("새 플레이어를 생성한다.") {
            val actual = Player("최진영")
            actual.name shouldBe "최진영"
            actual.gameState.shouldBeTypeOf<InitialHand>()
        }
    }
})
