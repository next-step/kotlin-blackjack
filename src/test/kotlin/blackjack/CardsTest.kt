package blackjack

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {

    @Test
    fun `중복된 카드가 있으면 RuntimeException 예외가 발생한다`() {
        assertThrows<RuntimeException> {
            Cards(
                listOf(
                    Card(Denomination.ACE, Suit.CLOVER),
                    Card(Denomination.ACE, Suit.CLOVER)
                )
            )
        }
    }
}
