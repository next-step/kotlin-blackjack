package blackJack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardTest {

    @Test
    fun `올바른 Suit 와 Rank 가 입력되면 카드가 생성된다`() {
        val suit = Suit.CLUB
        val rank = Rank.ACE
        val card = Card(suit, rank)
    }

    @Test
    fun `잘못된 Suit 로 카드를 만들려고 할 때 IllegalArgumentException이 발생한다`() {
        val suit = "INVALID_SUIT"
        val rank = Rank.KING
        assertThrows<IllegalArgumentException> {
            Card(Suit.valueOf(suit), rank)
        }
    }

    @Test
    fun `잘못된 Rank 로 카드를 만들려고 할 때 IllegalArgumentException이 발생한다`() {
        val suit = Suit.DIAMOND
        val rank = "INVALID_RANK"
        assertThrows<IllegalArgumentException> {
            Card(suit, Rank.valueOf(rank))
        }
    }
}
