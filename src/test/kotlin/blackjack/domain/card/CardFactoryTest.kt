package blackjack.domain.card

import blackjack.domain.card.CardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class CardFactoryTest {

    @Test
    fun `총 52장의 카드를 만든다`() {
        assertThat(CardFactory.cards.size).isEqualTo(52)
    }
}
