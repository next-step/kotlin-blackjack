package blackject

import blackject.model.Dealer
import blackject.model.card.Card
import blackject.model.card.CardNumber
import blackject.model.card.CardType
import blackject.model.card.Cards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    @DisplayName("딜러의 이름 확인")
    fun `check name of dealer`() {
        val expectedValue = "딜러"

        val dealer = Dealer()

        Assertions.assertThat(dealer.name).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16인 경우 추가적으로 뽑을 수 있다")
    fun `check condition of taking card when dealer has 10`() {
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.SIX),
                Card(CardType.CLOVER, CardNumber.JACK)
            )
        )
        val dealer = Dealer(cards = cards)
        val expectedValue = true

        Assertions.assertThat(dealer.canTakeMoreCard()).isEqualTo(expectedValue)
    }

    @Test
    @DisplayName("딜러의 카드가 17인 경우 추가적으로 카드를 뽑을 수 없다.")
    fun `check condition of taking card when dealer has over 16`() {
        val cards = Cards(
            mutableListOf(
                Card(CardType.CLOVER, CardNumber.SEVEN),
                Card(CardType.CLOVER, CardNumber.TEN),
            )
        )
        val dealer = Dealer(cards = cards)
        val expectedValue = false

        Assertions.assertThat(dealer.canTakeMoreCard()).isEqualTo(expectedValue)
    }
}
