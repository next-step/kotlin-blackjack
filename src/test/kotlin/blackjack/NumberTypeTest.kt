package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.NumberType
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NumberTypeTest {

    @Test
    fun `ACE의 score는 이전 score와 11을 더해서 21을 넘지 않을 경우 11로 사용한다`() {
        val cards = Cards.from(emptyList())

        val tenCard = Card(SuitType.SPADE, NumberType.TEN)
        cards.addCard(tenCard)

        val aceCard = Card(SuitType.SPADE, NumberType.ACE)
        cards.addCard(aceCard)

        assertThat(NumberType.getScore(cards)).isEqualTo(21)
    }

    @Test
    fun `ACE의 score는 이전 score와 11을 더해서 21을 넘을 경우 1로 사용한다`() {
        val cards = Cards.from(emptyList())

        val tenCard = Card(SuitType.SPADE, NumberType.TEN)
        cards.addCard(tenCard)

        val twoCard = Card(SuitType.SPADE, NumberType.TWO)
        cards.addCard(twoCard)

        val aceCard = Card(SuitType.SPADE, NumberType.ACE)
        cards.addCard(aceCard)

        assertThat(NumberType.getScore(cards)).isEqualTo(13)
    }
}
