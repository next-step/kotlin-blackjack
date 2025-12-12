package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlackjackCardsTest {
    @Test
    @DisplayName("카드를 올바르게 수령한다.")
    fun receiveCardTest() {
        // given
        val blackjackCards = BlackjackCards()

        // when
        blackjackCards.receiveCard(Card(Suit.HEART, CardValue.TEN))
        blackjackCards.receiveCard(Card(Suit.SPADE, CardValue.ACE))

        // then
        assertThat(blackjackCards.displayCardInfo()).isEqualTo("10하트, A스페이드")
    }

    @Test
    @DisplayName("수령한 카드에 맞는 점수가 올바르게 계산된다.")
    fun calculateScoreTest() {
        // given
        val blackjackCards = BlackjackCards()
        blackjackCards.receiveCard(Card(Suit.HEART, CardValue.TEN))
        blackjackCards.receiveCard(Card(Suit.SPADE, CardValue.TWO))

        // when
        val score = blackjackCards.calculateScore()

        // then
        assertThat(score).isEqualTo(12)
    }

    @Test
    @DisplayName("수령한 카드에 에이스가 포함되어도 점수가 올바르게 계산된다.")
    fun calculateScoreTest2() {
        // given
        val blackjackCards = BlackjackCards()
        blackjackCards.receiveCard(Card(Suit.HEART, CardValue.TEN))
        blackjackCards.receiveCard(Card(Suit.SPADE, CardValue.ACE))
        blackjackCards.receiveCard(Card(Suit.DIAMOND, CardValue.ACE))

        // when
        val score = blackjackCards.calculateScore()

        // then
        assertThat(score).isEqualTo(12)
    }
}
