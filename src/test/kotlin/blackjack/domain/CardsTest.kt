package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `52장의 카드가 생성되었는지 확인한다`() {
        // given, when
        val cards = Cards.of()
        // then
        assertEquals(Cards.SIZE, cards.value.size)
    }

    @Test
    fun `52장의 카드 중 한 장을 뽑으면 51장의 카드가 남는다`() {
        // given
        val cards = Cards.of()
        // when
        cards.dec()
        // then
        assertEquals(Cards.SIZE-1, cards.value.size)
    }
}
