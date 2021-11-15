package blackjack.domain.card

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Symbol
import blackjack.domain.card.Type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class HandTest {

    @Test
    fun `빈 Hand 생성`() {
        val hand = Hand.createEmpty()

        assertThat(hand.cards).isEmpty()
    }

    @Test
    fun `Hand에 카드 추가`() {
        val card = Card(Symbol.ACE, Type.CLUB)
        val hand = Hand.createEmpty()

        hand.add(card)

        assertThat(hand.cards.size).isEqualTo(1)
        assertThat(hand.cards[0]).isEqualTo(card)
    }

    @Test
    fun `Hand의 Score 계산 - Ace가 없을 경우`() {
        val hand = Hand(listOf(
            Card(Symbol.TWO, Type.CLUB),
            Card(Symbol.THREE, Type.CLUB),
            Card(Symbol.FOUR, Type.CLUB),
        ))

        val result = hand.score

        assertThat(result.value).isEqualTo(9)
    }

    @Test
    fun `Hand의 Score 계산 - Ace 보정을 받지 않는 경우`() {
        val hand = Hand(listOf(
            Card(Symbol.ACE, Type.CLUB),
            Card(Symbol.THREE, Type.CLUB),
            Card(Symbol.FOUR, Type.CLUB),
            Card(Symbol.TEN, Type.CLUB),
        ))

        val result = hand.score

        assertThat(result.value).isEqualTo(18)
    }

    @Test
    fun `Hand의 Score 계산 - Ace 보정을 받는 경우`() {
        val hand = Hand(listOf(
            Card(Symbol.ACE, Type.CLUB),
            Card(Symbol.THREE, Type.CLUB),
            Card(Symbol.FOUR, Type.CLUB),
        ))

        val result = hand.score

        assertThat(result.value).isEqualTo(18)
    }
}
