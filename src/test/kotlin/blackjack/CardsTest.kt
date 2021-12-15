package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.Score
import blackjack.domain.card.SuitType
import blackjack.state.CARD_HEART_ACE
import blackjack.state.CARD_HEART_FOUR
import blackjack.state.CARD_HEART_KING
import blackjack.state.CARD_HEART_TEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    @Test
    fun `Cards에 Card를 추가할 수 있다`() {
        var cards = Cards.from(emptyList())

        cards += Card(suitType = SuitType.SPADE, denomination = Denomination.EIGHT)

        assertThat(cards.cards.firstOrNull()).isEqualTo(
            Card(
                suitType = SuitType.SPADE,
                denomination = Denomination.EIGHT
            )
        )
    }

    @Test
    fun `Cards에 포함된 Card들의 score를 계산할 수 있다`() {
        var cards = Cards.from(emptyList())

        cards += Card(suitType = SuitType.SPADE, denomination = Denomination.EIGHT)
        cards += Card(suitType = SuitType.SPADE, denomination = Denomination.FOUR)
        cards += Card(suitType = SuitType.SPADE, denomination = Denomination.THREE)

        assertThat(cards.getScore().score).isEqualTo(15)
    }

    @Test
    fun `Cards는 ACE의 score를 이전 score와 11을 더해서 21을 넘지 않을 경우 11로 사용한다`() {
        var cards = Cards.from(emptyList())

        val tenCard = Card(SuitType.SPADE, Denomination.TEN)
        cards += tenCard

        val aceCard = Card(SuitType.SPADE, Denomination.ACE)
        cards += aceCard

        cards.getScore()
        assertThat(cards.getScore()).isEqualTo(Score(21))
    }

    @Test
    fun `Cards는 ACE의 score를 이전 score와 11을 더해서 21을 넘을 경우 1로 사용한다`() {
        var cards = Cards.from(emptyList())

        val tenCard = Card(SuitType.SPADE, Denomination.TEN)
        cards += tenCard

        val twoCard = Card(SuitType.SPADE, Denomination.TWO)
        cards += twoCard

        val aceCard = Card(SuitType.SPADE, Denomination.ACE)
        cards += aceCard

        assertThat(cards.getScore()).isEqualTo(Score(13))
    }

    @Test
    fun `Cards의 Score가 21이면 isBlackjack이 true이다`() {
        val cards = Cards.from(listOf(CARD_HEART_ACE, CARD_HEART_KING))

        val isBlackjack = cards.isBlackjack

        assertThat(isBlackjack).isTrue
    }

    @Test
    fun `Cards의 Score가 21을 넘으면 isBust는 true이다`() {
        val cards = Cards.from(listOf(CARD_HEART_TEN, CARD_HEART_KING, CARD_HEART_FOUR))

        val isBlackjack = cards.isBust

        assertThat(isBlackjack).isTrue
    }

    @Test
    fun `Cards의 갯수가 0이면 isInitial은 true이다`() {
        val emptyCards = Cards.from(emptyList())
        val isInitial = emptyCards.isInitial
        assertThat(isInitial).isTrue
    }

    @Test
    fun `Cards의 갯수가 1이면 isInitial은 true이다`() {
        val oneCards = Cards.from(listOf(CARD_HEART_TEN))
        val isInitial = oneCards.isInitial
        assertThat(isInitial).isTrue
    }

    @Test
    fun `Cards의 갯수가 2이면 isInitial은 false이다`() {
        val twoCards = Cards.from(listOf(CARD_HEART_TEN, CARD_HEART_ACE))
        val isInitial = twoCards.isInitial
        assertThat(isInitial).isFalse
    }
}
