package blackjack.domain.player

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.finished.Stay
import blackjack.domain.gamestate.running.Hit
import blackjack.domain.gamestate.running.InitialHand
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class PlayerTest : FunSpec({

    context("init") {
        test("새 플레이어를 생성한다.") {
            val actual = Player(Name("최진영"))
            actual.name.value shouldBe "최진영"
            actual.gameState.shouldBeTypeOf<InitialHand>()
        }
    }

    context("draw") {
        test("추가된 다음 게임상태로 변경된다.") {
            val player = Player(Name("최진영"), InitialHand(Cards.of(SPADE_ACE)))
            player.draw(SPADE_TWO)

            player.gameState.shouldBeTypeOf<Hit>()
        }
    }

    context("stay") {
        test("다음 상태를 stay로 변경한다.") {
            val player = Player(Name("최진영"), Hit(Cards.of(SPADE_ACE, SPADE_KING)))
            player.stay()

            player.gameState.shouldBeTypeOf<Stay>()
        }
    }
})
