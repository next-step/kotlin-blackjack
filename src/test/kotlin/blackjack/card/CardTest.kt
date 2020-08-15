package blackjack.card

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardTest {

    @DisplayName(value = "카트 번호가 1인 경우, 카드는 Ace이다.")
    @Test
    fun checkIsAce() {
        val card = Card(CardType.DIAMONDS, CardNumber.ONE)
        Assertions.assertThat(card.isAce()).isTrue()
    }
}
