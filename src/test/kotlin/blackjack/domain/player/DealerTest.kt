package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.card.CardsDeck
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `현재 가지고 있는 카드의 총합이 16이하면 카드를 한장 더 받는다`() {
        val dealer = Dealer(Participant("딜러"))

        val card = dealer.addCardWhenLessThanStandard(CardsDeck())

        assertNotNull(card)
    }

    @Test
    fun `현재 가지고 있는 카드의 총합이 17이상이면 카드를 받지 않는다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            dealer.addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            dealer.addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
        }

        val card = dealer.addCardWhenLessThanStandard(CardsDeck())

        assertNull(card)
    }
}
