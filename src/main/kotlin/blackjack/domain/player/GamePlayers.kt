package blackjack.domain.player

import blackjack.domain.BlackjackGameResult
import blackjack.domain.DealerResult
import blackjack.domain.PlayerResult
import blackjack.domain.card.Cards

class GamePlayers(val players: List<Player>, val dealer: Dealer = Dealer()) {
    fun receiveCard(getCards: () -> Cards) {
        players.plus(dealer).forEach {
            val cards = getCards()
            it.initCards(cards)
        }
    }

    fun getGameResult(): BlackjackGameResult {
        val dealerScore = dealer.cards.getOptimizedScore()
        val playerResults = players.map { compareScore(it, dealerScore) }
        val dealerResult = DealerResult.from(dealer, playerResults)
        return BlackjackGameResult(dealerResult, playerResults)
    }

    private fun compareScore(player: Player, dealerScore: Int): PlayerResult {
        val playerScore = player.cards.getOptimizedScore()
        val gameResult = when {
            dealerScore > PlayerStatus.BLACK_JACK_SCORE ||
                (playerScore <= PlayerStatus.BLACK_JACK_SCORE && dealerScore < playerScore) -> GameResult.WIN
            (playerScore == dealerScore) -> GameResult.TIE
            else -> GameResult.LOOSE
        }
        return PlayerResult(player, gameResult)
    }

    companion object {
        fun from(vararg player: Player): GamePlayers {
            return GamePlayers(player.toList())
        }
    }
}
