package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardsTest {
    @DisplayName("card가 추가되면 카드 목록의 사이즈는 1 늘어난다.")
    @Test
    fun `card size added`() {
        val card = Cards()
        card.addCard()
        println(card.getCards().size)
        assertThat(card.getCards().size).isEqualTo(3)
    }
}
