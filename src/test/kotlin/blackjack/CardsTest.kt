package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `Cards에 Card를 추가할 수 있다`() {
        val cards = Cards.from(emptyList())

        cards.addCard(Card(suitType = SuitType.SPADE, denomination = Denomination.EIGHT))

        assertThat(cards.cards.firstOrNull()).isEqualTo(Card(suitType = SuitType.SPADE, denomination = Denomination.EIGHT))
    }

    @Test
    fun `Cards에 포함된 Card들의 score를 계산할 수 있다`() {
        val cards = Cards.from(emptyList())

        cards.addCard(Card(suitType = SuitType.SPADE, denomination = Denomination.EIGHT))
        cards.addCard(Card(suitType = SuitType.SPADE, denomination = Denomination.FOUR))
        cards.addCard(Card(suitType = SuitType.SPADE, denomination = Denomination.THREE))

        assertThat(cards.getScore().score).isEqualTo(15)
    }
}
