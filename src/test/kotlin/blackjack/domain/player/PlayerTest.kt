package blackjack.domain.player

import blackjack.domain.card.CardTest
import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_TWO
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.finished.Blackjack
import blackjack.domain.gamestate.finished.Bust
import blackjack.domain.gamestate.finished.Stay
import blackjack.domain.gamestate.running.Hit
import blackjack.domain.gamestate.running.InitialHand
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeTypeOf
import java.lang.IllegalStateException

class PlayerTest : FunSpec({

    context("init") {
        test("새 플레이어를 생성한다.") {
            val actual = Player(Name("최진영"), Money(10_000))
            actual.name.value shouldBe "최진영"
            actual.gameState.shouldBeTypeOf<InitialHand>()
        }
    }

    context("equals") {
        test("이름이 같아도 같은 플레이어가 아니다.") {
            val player1 = Player(Name("최진영"), Money(10_000))
            val player2 = Player(Name("최진영"), Money(10_000))
            player1 shouldNotBe player2
        }
    }

    context("draw") {
        test("추가된 다음 게임상태로 변경된다.") {
            val player = Player(Name("최진영"), Money(10_000), InitialHand(Cards.of(SPADE_ACE)))
            player.draw(SPADE_TWO)

            player.gameState.shouldBeTypeOf<Hit>()
        }
    }

    context("stay") {
        test("다음 상태를 stay로 변경한다.") {
            val player = Player(Name("최진영"), Money(10_000), Hit(Cards.of(SPADE_ACE, SPADE_TWO)))
            player.stay()

            player.gameState.shouldBeTypeOf<Stay>()
        }
    }

    context("competeWith") {
        test("딜러가 아닌 참가자와 승부하려고하면 예외가 발생한다.") {
            val player = Player(Name("a"), Money(10_000), Stay(Cards.of(SPADE_KING, SPADE_ACE)))
            val player2 = Player(Name("ㅠ"), Money(10_000), Stay(Cards.of(SPADE_KING, SPADE_ACE)))
            val exception = shouldThrowExactly<IllegalArgumentException> { player.competeWith(player2) }
            exception.message shouldBe "딜러는 플레이어와만 승부할 수 있습니다."
        }

        test("승부를 확인한다.") {
            val player = Player(Name("a"), Money(10_000), Blackjack(Cards.of(SPADE_KING, SPADE_ACE)))
            val dealer = Dealer(gameState = Stay(Cards.of(SPADE_KING, CardTest.SPADE_JACK)))
            val actual = player.competeWith(dealer)

            actual shouldBe 15_000
        }
    }
})
