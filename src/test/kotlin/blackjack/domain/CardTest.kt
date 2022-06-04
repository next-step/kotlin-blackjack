package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    internal fun `덱에 들어갈 카드 생성 테스트`() {
        val cards = Card.createDeck()
        assertThat(cards.count()).isEqualTo(52)
    }
}
