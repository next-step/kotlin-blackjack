package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DeckTest {
    @Test
    fun `덱에서는 최대 52개까지의 카드를 가지고 있고 그 이상 카드를 뽑아내려 했을 때 예외가 발생한다`() {
        val deck = Deck()
        (1..52).forEach { _ ->
            deck.pick()
        }

        assertThrows<IllegalStateException> {
            deck.pick()
        }
    }
}
