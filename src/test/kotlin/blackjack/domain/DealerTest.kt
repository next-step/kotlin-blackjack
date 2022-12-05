package blackjack.domain

import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 총 52장 카드의 덱을 가진다`() {
        Dealer().cards.list
    }
}
