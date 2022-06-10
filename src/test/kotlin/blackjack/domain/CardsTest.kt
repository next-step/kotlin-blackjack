package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by Jaesungchi on 2022.06.10..
 */
class CardsTest {
    @Test
    internal fun `카드 추가시 추가가 잘 된다`() {
        val cards = Cards().apply { addCard() }
        assertThat(cards.cards).hasSize(1)
    }
}
