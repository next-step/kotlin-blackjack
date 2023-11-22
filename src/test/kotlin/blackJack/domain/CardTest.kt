package blackJack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드가 랜덤하게 뽑힌다`() {
        val card = Card.drawCard()
        assertNotNull(card)
    }

    @Test
    fun `입력한 수 만큼 카드가 뽑힌다`() {
        val dealer = Dealer()
        val cards = dealer.betting()
        assertEquals(2, cards.cards.size)
    }
}
