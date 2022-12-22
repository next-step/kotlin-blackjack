package blackjack.domain

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.state.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `게임 매니저 - 블랙잭 확인 테스트`() {
        // given
        val player1 = Player(Name("pobi"), PlayingCards(PlayingCard(Suit.CLUBS, Denomination.ACE), PlayingCard(Suit.CLUBS, Denomination.JACK)))
        val player2 = Player(Name("pobi"), PlayingCards(PlayingCard(Suit.CLUBS, Denomination.JACK), PlayingCard(Suit.CLUBS, Denomination.JACK)))
        val players = Players(player1, player2)

        // when, then
        assertThat(GameManager.checkBlackjack(players)).isTrue
    }
}
