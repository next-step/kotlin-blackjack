package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardTest {
    @DisplayName("카드 번호에 맞는 점수를 리턴한다.")
    @Test
    fun `get card score`() {
        val card = Card(Symbol.HEART, Numbers.SEVEN)
        assertThat(card.getCardScore()).isEqualTo(7)
    }

    @DisplayName("Card 인스턴스를 반환한다.")
    @Test
    fun `get card instances`() {
        assertThat(Card.getInstances()).isExactlyInstanceOf(Card::class.java)
    }
}
