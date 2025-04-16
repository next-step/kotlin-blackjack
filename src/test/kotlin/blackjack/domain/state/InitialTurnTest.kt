package blackjack.domain.state

import blackjack.domain.Hands
import blackjack.domain.card.Card
import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.card.CardFixture.SPADES_SIX
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class InitialTurnTest : FunSpec({
    context("addCard") {
        test("if not initialized, should return initial turn state") {
            val initialTurn = InitialTurn()
            val newCard = Card.of(CardNumber.ACE, Suit.HEARTS)
            val state = initialTurn.addCard(newCard)

            assertSoftly {
                state.hands.size shouldBe 1
                state::class shouldBe InitialTurn::class
            }
        }

        test("if initialized, should return hit state") {
            val initialTurn =
                InitialTurn(
                    Hands(
                        listOf(
                            SPADES_ACE,
                            SPADES_SIX,
                        ),
                    ),
                )
            val state = initialTurn.addCard(SPADES_ACE)

            assertSoftly {
                state.hands.size shouldBe 3
                state::class shouldBe Hit::class
            }
        }
    }
})
