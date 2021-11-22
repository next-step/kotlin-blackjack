package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.NumberType
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardsTest {

    @DisplayName(value = "Cards에 Card를 추가할 수 있다")
    @Test
    fun `Cards에 Card를 추가할 수 있다`() {
        val cards = Cards.from()

        cards.addCard(Card(suitType = SuitType.SPADE, numberType = NumberType.EIGHT))

        assertThat(cards[0]).isEqualTo(Card(suitType = SuitType.SPADE, numberType = NumberType.EIGHT))
    }

    @DisplayName(value = "Cards에 포함된 Card들의 score를 계산할 수 있다")
    @Test
    fun `Cards에 포함된 Card들의 score를 계산할 수 있다`() {
        val cards = Cards.from()

        cards.addCard(Card(suitType = SuitType.SPADE, numberType = NumberType.EIGHT))
        cards.addCard(Card(suitType = SuitType.SPADE, numberType = NumberType.FOUR))
        cards.addCard(Card(suitType = SuitType.SPADE, numberType = NumberType.THREE))

        assertThat(cards.getScore()).isEqualTo(15)
    }
}
