package blackjack.domain

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드는 모양과 숫자를 가지고 생성한다`() {
        val card = Card.of(Suit.HEART, Rank.ACE)
        assertEquals(Suit.HEART, card.suit)
        assertEquals(Rank.ACE, card.rank)
    }

    @Test
    fun `카드는 모양과 숫자가 같으면 같은 카드이다`() {
        val card1 = Card.of(Suit.HEART, Rank.ACE)
        val card2 = Card.of(Suit.HEART, Rank.ACE)
        assertEquals(card1, card2)
    }
}
