package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.card.CardNumber.ACE
import blackjack.domain.card.Suit.SPADES
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

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
                    mutableListOf(
                        Card.of(ACE, SPADES),
                        Card.of(ACE, SPADES),
                    )
                }
            }
        }
    }

    context("drawCard") {
        test("should draw a card and remove from deck") {
            val deck = Deck.create { mutableListOf(SPADES_ACE) }
            val card = deck.drawCard()

            card shouldBe SPADES_ACE
        }

        test("should throw exception if no card to draw") {
            val deck = Deck.create { mutableListOf() }

            shouldThrow<IllegalStateException> {
                deck.drawCard()
            }
        }
    }
})
