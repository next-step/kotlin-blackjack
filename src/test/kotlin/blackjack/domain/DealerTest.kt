package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 Deck에서 한장씩 카드를 뽑는다`() {
        // given
        val dealer = Dealer()

        // when
        repeat(Cards.TOTAL_SIZE) {
            println(dealer.dealCard())
        }

        // then
        assertEquals(0, dealer.deck.cardSize)
    }
}
