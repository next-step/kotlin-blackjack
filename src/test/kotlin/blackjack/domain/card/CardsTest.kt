package blackjack.domain.card

import blackjack.domain.card.Denomination.ACE
import blackjack.domain.card.Suit.SPADES
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class CardsTest : StringSpec({

    "중복된 Card가 있을 때, 예외가 발생한다." {
        val card1 = Card(ACE, SPADES)
        val card2 = Card(ACE, SPADES)

        shouldThrow<IllegalArgumentException> {
            Cards(listOf(card1, card2))
        }
    }
})
