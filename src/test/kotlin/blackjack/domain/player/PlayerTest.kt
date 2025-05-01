package blackjack.domain.player

import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.card.CardFixture.SPADES_SEVEN
import blackjack.domain.card.CardFixture.SPADES_SIX
import blackjack.domain.card.CardFixture.SPADES_TWO
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.InitialState
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("when created, state should be initial state") {
        val player = Player("me")

        player.state::class shouldBe InitialState::class
    }

    context("canDraw") {
        test("return true on initial state") {
            val player = Player("sun")
            player.canDraw shouldBe true
        }

        test("return true on hit state") {
            val player = Player("sun", Hit(Hands()))
            player.canDraw shouldBe true
        }

        test("return false on bust state") {
            val player = Player("sun", Bust(Hands()))
            player.canDraw shouldBe false
        }
    }

    test("can draw a card") {
        val player = Player("me")
        player.draw(SPADES_ACE)

        player.state.hands.size shouldBe 1
    }

    test("state should change") {
        val player = Player("me")
        player.draw(SPADES_SIX)
        player.draw(SPADES_SEVEN)
        player.draw(SPADES_TWO)

        assertSoftly {
            player.state.hands.size shouldBe 3
            player.state::class shouldBe Hit::class
        }
    }
})
