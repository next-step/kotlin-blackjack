package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `draw를 통해 원하는 만큼 카드를 뽑을 수 있다`() {
        val cardDeck = CardDeck.create()

        assertThat(cardDeck.draw(5).size).isEqualTo(5)
    }

    @Test
    fun `남은 장 수 이상의 카드를 요청할 경우 모든 카드를 뽑는다`() {
        val cardDeck = CardDeck.create()

        assertThat(cardDeck.draw(100).size).isEqualTo(52)
    }
}
