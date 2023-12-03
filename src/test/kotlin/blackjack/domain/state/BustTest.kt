package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class BustTest : StringSpec({
    "버스트가 되면 카드를 받을 수 없다." {
        val bust = Bust(cards = Cards())

        shouldThrow<IllegalStateException> {
            val card = Card(number = CardNumber.TWO, pattern = CardPattern.HEART)
            bust.receiveCard(card)
        }
    }
})
