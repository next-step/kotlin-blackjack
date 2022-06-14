package blackjack

import blackjack.domain.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 한 팩은 48장이다`() {
        assertThat(Card.pack().size).isEqualTo(48)
    }
}
