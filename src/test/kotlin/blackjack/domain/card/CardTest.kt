package blackjack.domain.card

import blackjack.domain.card.CardNumber.ACE
import blackjack.domain.card.CardNumber.NINE
import blackjack.domain.card.CardNumber.QUEEN
import blackjack.domain.card.CardNumber.TWO
import blackjack.domain.card.Suit.CLUBS
import blackjack.domain.card.Suit.DIAMONDS
import blackjack.domain.card.Suit.HEARTS
import blackjack.domain.card.Suit.SPADES
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    context("card creation") {
        test("should create with valid enum values") {
            this@context.withData(
                ACE to SPADES,
                QUEEN to HEARTS,
            ) { (number, suit) ->
                shouldNotThrowAny {
                    Card.of(number, suit)
                }
            }
        }

        test("should create with raw values") {
            this@context.withData(
                "ACE" to "CLUBS",
                "QUEEN" to "HEARTS",
                "KING" to "DIAMONDS",
            ) { (number, suit) ->
                shouldNotThrowAny {
                    Card.of(number, suit)
                }
            }
        }

        test("should throw exception with invalid values") {
            this@context.withData(
                "TRIPLE" to "CLUBS",
                "TWO" to "CLOVER",
                "ACE" to "DIAMOND",
                "ACE" to "SPACEX",
            ) {
                shouldThrow<IllegalArgumentException> {
                    Card.of(it.first, it.second)
                }
            }
        }

        test("same number and suit should have save reference") {
            this@context.withData(
                ACE to SPADES,
                QUEEN to HEARTS,
                TWO to DIAMONDS,
                NINE to CLUBS,
            ) { (number, suit) ->
                val card1 = Card.of(number, suit)
                val card2 = Card.of(number, suit)

                (card1 === card2) shouldBe true
            }
        }
    }
})
