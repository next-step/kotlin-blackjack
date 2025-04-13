package blackjack.domain.card

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData

class CardTest : FunSpec({
    context("card creation") {
        test("should create with valid enum values") {
            this@context.withData(
                CardNumber.ACE to Suit.SPADES,
                CardNumber.QUEEN to Suit.HEARTS,
            ) { (number, suit) ->
                shouldNotThrowAny {
                    Card(number, suit)
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
                    Card(number, suit)
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
                    Card(it.first, it.second)
                }
            }
        }
    }
})
