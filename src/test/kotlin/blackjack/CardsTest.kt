package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardsTest {
    private val card = Cards(mutableListOf(Card(Symbol.HEART, Numbers.SEVEN), Card(Symbol.HEART, Numbers.JACK)))

    @DisplayName("카드 추가되면 카드 목록의 사이즈는 1 늘어난다.")
    @Test
    fun `card size added`() {
        card.addCard(Card(Symbol.HEART, Numbers.FOUR))
        assertThat(card.getCards().size).isEqualTo(3)
    }

    @DisplayName("카드 번호들의 숫자를 더한다.")
    @Test
    fun `add card numbers`() {
        assertThat(card.sumCardNumbers()).isEqualTo(17)
    }

    @DisplayName("카드 번호의 합이 21보다 크면 true를 반환한다.")
    @Test
    fun `when card sum is greater than 21 than return true`() {
        assertThat(card.isGraterThanWinScore(Card(Symbol.HEART, Numbers.JACK))).isTrue()
    }
}
