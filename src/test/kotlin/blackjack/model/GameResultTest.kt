package blackjack.model

import blackjack.model.Denomination.ACE
import blackjack.model.Denomination.JACK
import blackjack.model.Denomination.NINE
import blackjack.model.Denomination.TEN
import blackjack.model.Suit.HEART
import blackjack.model.Suit.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    internal fun `승부 결과를 가져온다`() {
        // given
        val dealerCards = Cards.of(Card(SPADE, TEN), Card(HEART, TEN))
        val player1Cards = Cards.of(Card(SPADE, TEN), Card(HEART, TEN))
        val player2Cards = Cards.of(Card(SPADE, TEN), Card(HEART, NINE))
        val player3Cards = Cards.of(Card(SPADE, ACE), Card(HEART, JACK))
        val dealer = Player("딜러", dealerCards)
        val player1 = Player("A", player1Cards)
        val player2 = Player("B", player2Cards)
        val player3 = Player("C", player3Cards)
        val players = Players.of(player1, player2, player3)

        // when
        val gameResult = GameResult.of(dealer, players)

        // then
        assertThat(gameResult.getDealerResultCountOf(DualResult.WIN)).isEqualTo(1)
        assertThat(gameResult.getDealerResultCountOf(DualResult.PUSH)).isEqualTo(1)
        assertThat(gameResult.getDealerResultCountOf(DualResult.LOSE)).isEqualTo(1)
        assertThat(gameResult.getPlayerDualResultOf(player1)).isEqualTo(DualResult.PUSH)
        assertThat(gameResult.getPlayerDualResultOf(player2)).isEqualTo(DualResult.LOSE)
        assertThat(gameResult.getPlayerDualResultOf(player3)).isEqualTo(DualResult.WIN)
    }
}
