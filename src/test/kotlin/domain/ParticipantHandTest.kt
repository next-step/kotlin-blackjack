package domain

import domain.card.Card
import domain.card.CardValue
import domain.card.Suit
import domain.participant.ParticipantHand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParticipantHandTest {
    @Test
    @DisplayName("카드를 올바르게 수령한다.")
    fun receiveCardTest() {
        // given
        val participantHand = ParticipantHand()

        // when
        participantHand.receiveCard(Card(Suit.HEART, CardValue.TEN))
        participantHand.receiveCard(Card(Suit.SPADE, CardValue.ACE))

        // then
        assertThat(participantHand.describeHand()).isEqualTo("10하트, A스페이드")
    }

    @Test
    @DisplayName("수령한 카드에 맞는 점수가 올바르게 계산된다.")
    fun calculateScoreTest() {
        // given
        val participantHand = ParticipantHand()
        participantHand.receiveCard(Card(Suit.HEART, CardValue.TEN))
        participantHand.receiveCard(Card(Suit.SPADE, CardValue.TWO))

        // when
        val score = participantHand.calculateScore()

        // then
        assertThat(score).isEqualTo(12)
    }

    @Test
    @DisplayName("수령한 카드에 에이스가 포함되어도 점수가 올바르게 계산된다.")
    fun calculateScoreTest2() {
        // given
        val participantHand = ParticipantHand()
        participantHand.receiveCard(Card(Suit.HEART, CardValue.TEN))
        participantHand.receiveCard(Card(Suit.SPADE, CardValue.ACE))
        participantHand.receiveCard(Card(Suit.DIAMOND, CardValue.ACE))

        // when
        val score = participantHand.calculateScore()

        // then
        assertThat(score).isEqualTo(12)
    }
}
