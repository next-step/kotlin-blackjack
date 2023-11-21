package blackjack.domain

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드 소진시 예외를 던진다`() {
        val dealer = Dealer()
        repeat(52) { dealer.supplyCard(dealer) }
        assertThrows(IllegalStateException::class.java) { dealer.supplyCard(dealer) }
    }
}
