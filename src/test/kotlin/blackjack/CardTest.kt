package blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드는 모양과 숫자를 가지고 생성한다`() {
        val card = Card(Suit.HEART, Rank.ACE)
        assertEquals(Suit.HEART, card.suit)
        assertEquals(Rank.ACE, card.rank)
    }
}
