package blackjack.domain

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.SpadeAce
import blackjack.domain.card.SpadeEight
import blackjack.domain.card.SpadeJack
import blackjack.domain.card.SpadeNine
import blackjack.domain.card.Suit
import blackjack.domain.participant.ParticipantFactory
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.Name
import blackjack.domain.participant.state.result.Result
import blackjack.domain.participant.state.role.DealerRole
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

    @Test
    fun `게임 매니저 - 결과 확인 테스트`() {
        // given
        val dealer = ParticipantFactory.create(Name(DealerRole.DEALER_NAME), PlayingCards(SpadeAce, SpadeJack))
        val player1 = ParticipantFactory.create(Name("pobi"), PlayingCards(SpadeAce, SpadeEight))
        val player2 = ParticipantFactory.create(Name("jason"), PlayingCards(SpadeAce, SpadeNine))
        val participants = Participants(dealer, player1, player2)

        // when
        val actual = GameManager.calculateResult(participants)

        // then
        assertThat(actual).isEqualTo(
            mapOf(
                player1 to Result.Lose,
                player2 to Result.Lose,
            )
        )
    }
}
