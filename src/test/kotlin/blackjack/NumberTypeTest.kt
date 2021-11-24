package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.NumberType
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NumberTypeTest {

    @Test
    fun `ACE의 score는 1 혹은 11이 될 수 있다`() {
        val cards = Cards()

        val ace1 = Card(SuitType.SPADE, NumberType.ACE)
        cards.addCard(ace1)
        assertThat(NumberType.getScore(cards)).isEqualTo(11)

        val ace2 = Card(SuitType.SPADE, NumberType.ACE)
        cards.addCard(ace2)
        assertThat(NumberType.getScore(cards)).isEqualTo(12)

        val ace3 = Card(SuitType.SPADE, NumberType.ACE)
        cards.addCard(ace3)
        assertThat(NumberType.getScore(cards)).isEqualTo(13)
    }
}
