package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class CardPackTest {

    @Test
    fun `카드를 다 사용 하면 신규로 52장을 추가 한다`() {
        val cardPack = CardPack()

        repeat(Card.ALL_CARD.size) { cardPack.next() }
        assertDoesNotThrow { cardPack.next() }
    }
}
