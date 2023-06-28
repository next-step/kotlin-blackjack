package blackjack.domain.player

import blackjack.domain.BlackjackGameResult
import blackjack.domain.DealerResult
import blackjack.domain.PlayerResult
import blackjack.domain.PlayerResults
import blackjack.domain.card.Cards

class GamePlayers(val players: List<Player>) {
    val dealer: Dealer = Dealer()

    fun receiveCard(getCards: () -> Cards) {
        players.plus(dealer).forEach {
            val cards = getCards()
            it.initCards(cards)
        }
    }

    fun finish(): BlackjackGameResult {
        val playerResults = getPlayerResults()
        val dealerResult = getDealerResult(playerResults)
        return BlackjackGameResult(dealerResult, playerResults)
    }

    private fun getPlayerResults(): PlayerResults {
        return PlayerResults(players.map { compareWithDealer(it) }).apply {
            this.calcRevenues()
        }
    }

    private fun getDealerResult(playerResults: PlayerResults): DealerResult {
        return DealerResult.from(dealer, playerResults)
    }

    private fun compareWithDealer(player: Player): PlayerResult {
        val dealerScore = dealer.cards.getOptimizedScore()
        val gameResult = GameResult.valueByCompare(dealerScore, player)
        return PlayerResult(player, gameResult)
    }

    companion object {
        fun from(vararg player: Player): GamePlayers {
            return GamePlayers(player.toList())
        }
    }
}
