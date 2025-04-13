package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber.ACE
import blackjack.domain.card.Suit.SPADES
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class DeckTest : FunSpec({
    context("create") {
        test("should create a Deck") {
            shouldNotThrowAny {
                Deck.create()
            }
        }

        test("should throw exception if duplicated cards exist") {
            shouldThrow<IllegalStateException> {
                Deck.create {
                    listOf(
                        Card(ACE, SPADES),
                        Card(ACE, SPADES),
                    )
                }
            }
        }
    }
})
