package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultCardFactoryTest {
    @Test
    fun `52장의 각기 다른 카드를 만든다`() {
        val cardSet = DefaultCardFactory().createCards().toSet()
        assertThat(cardSet).hasSize(52)
    }
}
