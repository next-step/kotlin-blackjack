package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardFactoryTest {

    @Test
    fun `카드가 생성되는 갯수는 총 52장이다`() {
        val actual = CardFactory.generateCards()

        assertThat(actual).hasSize(52)
    }
}
