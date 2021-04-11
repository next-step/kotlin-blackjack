package blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardFactoryTest {

    @Test
    fun `카드 개수 확인`() {
        assertThat(CardFactory.makeCards().size).isEqualTo(52)
    }

}