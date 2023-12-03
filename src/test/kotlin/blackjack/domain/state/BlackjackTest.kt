package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class BlackjackTest : StringSpec({

    "첫 턴이 진행되자마자 블랙잭 상태가 되면 카드를 받을 수 없다." {
        val blackjack = Blackjack(cards = Cards())

        shouldThrow<IllegalStateException> {
            val card = Card(number = CardNumber.TWO, pattern = CardPattern.HEART)
            blackjack.receiveCard(card)
        }
    }
})
