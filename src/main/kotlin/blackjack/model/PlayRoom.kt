package blackjack.model

import blackjack.model.player.Player
import blackjack.model.player.Players

class PlayRoom(
    private val cardDistributor: CardDistributor,
    private val players: Players,
    private val initialCardCountOfPlayer: Int
) {

    fun startNewGame() {
        this.cardDistributor.resetCardSet()
        this.players.clearCard()
        this.cardDistributor.giveCardsTo(this.players, initialCardCountOfPlayer)
    }

    fun playGame(progress: ((Player) -> Unit)? = null) {
        val players = this.players
        players.forEach { player ->
            players.blackJackPlayer?.let { return }
            player.hitWhileWants(cardDistributor, progress)
        }
    }
}
