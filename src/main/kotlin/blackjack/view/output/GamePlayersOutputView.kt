package blackjack.view.output

import blackjack.domain.player.GamePlayer
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.Player

class GamePlayersOutputView(gamePlayers: GamePlayers, private val isResult: Boolean = false) {
    init {
        with(gamePlayers) {
            listOf(dealer).plus(players).forEach { gamePlayer -> renderMessage(gamePlayer) }
        }
    }

    private fun renderMessage(gamePlayer: GamePlayer) {
        with(gamePlayer) {
            val resultScoreMessage = if (isResult) " - 결과: ${gamePlayer.cards.getOptimizedScore()}" else ""
            val message = "${getName(this)} 카드: "
            val cardsStr =
                this.cards.getValue().joinToString(SEPARATOR) { card -> card.denom.symbol + card.type.korName }
            println(message + cardsStr + resultScoreMessage)
        }
    }

    private fun getName(gamePlayer: GamePlayer): String {
        if (gamePlayer is Player) return gamePlayer.name.value
        return "딜러"
    }

    companion object {
        const val SEPARATOR = ", "
    }
}
