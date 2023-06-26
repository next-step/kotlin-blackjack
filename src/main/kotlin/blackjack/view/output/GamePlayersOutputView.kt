package blackjack.view.output

import blackjack.domain.card.Card
import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.Player

class GamePlayersOutputView(gamePlayers: GamePlayers, private val isResult: Boolean = false) {
    init {
        with(gamePlayers) {
            val hasDealer = dealer.cards.getValue().isNotEmpty()
            if (hasDealer) renderDealerMessage(dealer)
            players.forEach { gamePlayer -> renderPlayerMessage(gamePlayer) }
        }
    }

    private fun renderDealerMessage(dealer: Dealer) {
        val resultScoreMessage = getScoreMessage(dealer.cards.getOptimizedScore())
        val cardMessage = getCardStr(dealer.cards.getFirstCard())
        val message = "딜러: $cardMessage"
        println(message + resultScoreMessage)
    }

    private fun renderPlayerMessage(player: Player) {
        with(player) {
            val resultScoreMessage = getScoreMessage(player.cards.getOptimizedScore())
            val message = "${this.name.value} 카드: "
            val cardsStr =
                this.cards.getValue().joinToString(SEPARATOR) { card -> getCardStr(card) }
            println(message + cardsStr + resultScoreMessage)
        }
    }

    private fun getScoreMessage(score: Int): String {
        return if (isResult) " - 결과: $score" else ""
    }

    private fun getCardStr(card: Card): String {
        return card.denom.symbol + card.type.korName
    }

    companion object {
        const val SEPARATOR = ", "
    }
}
