package blackject

import blackject.model.card.CardsDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    @DisplayName("원하는 개수의 카드를 받을 수 있는지 확인")
    fun `take card number`() {
        val takeNumber = 3

        val card = CardsDeck.takeCard(takeNumber)

        assertThat(card.size).isEqualTo(takeNumber)
    }
}
