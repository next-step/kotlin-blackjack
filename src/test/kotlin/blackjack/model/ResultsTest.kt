package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ResultsTest {
    @Test
    fun `딜러, 플레이어의 결과를 생성한다`() {
        val player1 = Player("player1").addCards(listOf(Card(CardNumber.Ten, Suit.Heart), Card(CardNumber.Ten, Suit.Spade)))
        val player2 = Player("player2").addCards(listOf(Card(CardNumber.Nine, Suit.Club)))
        val players = Players(listOf(player1, player2))
        val dealer = Player.createDealer().addCards(listOf(Card(CardNumber.Ace, Suit.Club)))

        val results = Results(players, dealer)

        Assertions.assertThat(results.playerResults[0].player).isEqualTo(player1)
        Assertions.assertThat(results.playerResults[0].win).isEqualTo(true)

        Assertions.assertThat(results.playerResults[1].player).isEqualTo(player2)
        Assertions.assertThat(results.playerResults[1].win).isEqualTo(false)

        Assertions.assertThat(results.dealerResult.player).isEqualTo(dealer)
        Assertions.assertThat(results.dealerResult.win).isEqualTo(1)
        Assertions.assertThat(results.dealerResult.lose).isEqualTo(1)
    }
}
