package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Score
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

    @Test
    fun `Cards는 ACE의 score를 이전 score와 11을 더해서 21을 넘지 않을 경우 11로 사용한다`() {
        val cards = Cards.from(emptyList())

        val tenCard = Card(SuitType.SPADE, Denomination.TEN)
        cards.addCard(tenCard)

        val aceCard = Card(SuitType.SPADE, Denomination.ACE)
        cards.addCard(aceCard)

        cards.getScore()
        assertThat(cards.getScore()).isEqualTo(Score.from(21))
    }

    @Test
    fun `Cards는 ACE의 score를 이전 score와 11을 더해서 21을 넘을 경우 1로 사용한다`() {
        val cards = Cards.from(emptyList())

        val tenCard = Card(SuitType.SPADE, Denomination.TEN)
        cards.addCard(tenCard)

        val twoCard = Card(SuitType.SPADE, Denomination.TWO)
        cards.addCard(twoCard)

        val aceCard = Card(SuitType.SPADE, Denomination.ACE)
        cards.addCard(aceCard)

        assertThat(cards.getScore()).isEqualTo(Score.from(13))
    }
}
