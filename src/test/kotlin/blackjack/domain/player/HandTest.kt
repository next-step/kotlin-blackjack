package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HandTest {

    @Test
    fun `플레이어의 핸드에 카드를 추가할 수 있다`() {
        // given
        val hand = Hand()

        // when
        hand.add(DIAMOND_ACE)
            .add(CLUB_KING)
            .add(SPADE_TEN)

        // then
        assertThat(hand.cards).hasSize(3)
        assertThat(hand.cards).containsExactly(DIAMOND_ACE, CLUB_KING, SPADE_TEN)
    }

    @Test
    fun `플레이어의 핸드에 있는 카드의 총 점수를 계산할 수 있다`() {
        // given
        val hand = Hand()

        // when
        hand.add(DIAMOND_ACE)
            .add(CLUB_KING)
            .add(SPADE_TEN)
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(21)
    }

    companion object {
        val DIAMOND_ACE = Card(CardSuit.DIAMOND, CardSymbol.ACE)
        val CLUB_KING = Card(CardSuit.CLUB, CardSymbol.KING)
        val SPADE_TEN = Card(CardSuit.SPADE, CardSymbol.TEN)
    }
}
