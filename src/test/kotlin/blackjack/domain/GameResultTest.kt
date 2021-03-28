package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class GameResultTest {
    @DisplayName("플레이어들의 수익률 결과를 반환")
    @ParameterizedTest
    @MethodSource("providePlayer")
    fun initPlayerResult(player: Player, dealer: Dealer, profit: BettingMoney) {
        val result = GameResult(listOf(player), dealer)
        assertThat(result.players[player]).isEqualTo(profit)
    }

    @DisplayName("딜러의 수익률 결과를 반환")
    @Test
    fun dealer() {
        val bettingMoney = 1000
        val player = createPlayer("pobi", bettingMoney)
        player.draw(Card(CardSymbol.ACE, CardSuit.SPADE), DrawDecider.DRAW)

        val result = GameResult(listOf(player), Dealer())

        assertThat(result.dealer).isEqualTo(bettingMoney * -1)
    }

    companion object {
        @JvmStatic
        fun providePlayer(): List<Arguments> {
            val bettingMoney = 1000
            return listOf(
                Arguments.of(blackjackPlayer(bettingMoney), Dealer(), BettingMoney(bettingMoney * 1.5)),
                Arguments.of(winPlayer(bettingMoney), Dealer(), BettingMoney(bettingMoney * 1)),
                Arguments.of(drawPlayer(bettingMoney), Dealer(), BettingMoney(bettingMoney * 0)),
                Arguments.of(createPlayer("pobi", bettingMoney), winDealer(), BettingMoney(bettingMoney * -1)),
            )
        }

        private fun blackjackPlayer(money: Int): Player {
            val player = createPlayer("pobi", money)
            player.draw(Card(CardSymbol.ACE, CardSuit.SPADE), DrawDecider.DRAW)
            player.draw(Card(CardSymbol.KING, CardSuit.SPADE), DrawDecider.DRAW)
            return player
        }

        private fun winPlayer(money: Int): Player {
            val player = createPlayer("pobi", money)
            player.draw(Card(CardSymbol.ACE, CardSuit.SPADE), DrawDecider.DRAW)
            return player
        }

        private fun drawPlayer(money: Int): Player {
            return createPlayer("pobi", money)
        }

        private fun winDealer(): Dealer {
            val dealer = Dealer()
            dealer.draw(Card(CardSymbol.ACE, CardSuit.SPADE), DrawDecider.DRAW)
            return dealer
        }
    }
}
