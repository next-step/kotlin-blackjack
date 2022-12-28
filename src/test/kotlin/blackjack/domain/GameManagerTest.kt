package blackjack.domain

import blackjack.ClubJack
import blackjack.SpadeAce
import blackjack.SpadeEight
import blackjack.SpadeJack
import blackjack.SpadeNine
import blackjack.domain.card.PlayingCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.domain.participant.state.result.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `게임 매니저 - 블랙잭 확인 테스트`() {
        // given
        val player1 = Player("pobi", PlayingCards(SpadeAce, SpadeJack))
        val player2 = Player("jason", PlayingCards(SpadeJack, ClubJack))
        val participants = Participants(player1, player2)

        // when, then
        assertThat(GameManager.checkBlackjack(participants)).isTrue
    }

    @Test
    fun `게임 매니저 - 결과 확인 테스트`() {
        // given
        val dealer = Dealer(PlayingCards(SpadeAce, SpadeJack))
        val player1 = Player("pobi", PlayingCards(SpadeAce, SpadeEight))
        val player2 = Player("jason", PlayingCards(SpadeAce, SpadeNine))
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
