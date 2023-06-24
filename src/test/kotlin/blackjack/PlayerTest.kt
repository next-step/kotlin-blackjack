package blackjack

import blackjack.CardTest.Companion.SPADE_ACE
import blackjack.CardTest.Companion.SPADE_TWO
import blackjack.gamestate.Hit
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

    context("카드를 드로우한다.") {
        test("추가된 다음 게임상태로 변경된다.") {
            val player = Player("최진영", InitialHand(Cards.of(SPADE_ACE)))
            player.draw(SPADE_TWO)

            player.gameState.shouldBeTypeOf<Hit>()
        }
    }
})
