package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드팩은 52개의 카드 데이터를 가진다`() {
        assertThat(Card.CARD_PACK_CACHE).hasSize(52)
    }
}
