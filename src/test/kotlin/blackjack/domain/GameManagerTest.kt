package blackjack.domain

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import blackjack.domain.participant.ParticipantFactory
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `게임 매니저 - 블랙잭 확인 테스트`() {
        // given
        val player1 = ParticipantFactory.create(Name("pobi"), PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE), PlayingCard(Suit.CLUBS, Denomination.JACK)))
        val player2 = ParticipantFactory.create(Name("jason"), PlayingCards(PlayingCard(Suit.CLUBS, Denomination.JACK), PlayingCard(Suit.CLUBS, Denomination.JACK)))
        val participants = Participants(player1, player2)

        // when, then
        assertThat(GameManager.checkBlackjack(participants)).isTrue
    }
}
