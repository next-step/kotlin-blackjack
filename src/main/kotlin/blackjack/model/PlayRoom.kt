package blackjack.model

import blackjack.model.player.Player
import blackjack.model.player.Players

class PlayRoom(private val cardDistributor: CardDistributor, private val players: Players) {

    fun startNewGame() {
        this.cardDistributor.resetCardSet()
        this.players.clearCard()
        this.cardDistributor.giveCardsTo(this.players, 2)
    }

    fun playGame(progress: ((Player) -> Unit)? = null) {
        val players = this.players
        players.forEach { player ->
            players.blackJackPlayer?.let { return }
            player.hitWhileWants(cardDistributor, progress)
        }
    }
}
