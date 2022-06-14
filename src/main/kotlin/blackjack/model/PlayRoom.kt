package blackjack.model

import blackjack.model.player.Player
import blackjack.model.player.PlayerBets
import blackjack.model.player.PlayerRecords
import blackjack.model.player.Players
import blackjack.model.player.Players.Companion.toPlayers

class PlayRoom(
    val cardDistributor: CardDistributor,
    val dealer: Player.Dealer,
    val playerBets: PlayerBets
) {

    private val players: Players<Player> by lazy {
        (this.playerBets.map { it.player }.toMutableList() + dealer).toPlayers()
    }

    fun startNewGame() {
        this.cardDistributor.resetCardSet()
        this.players.clearCard()
        this.cardDistributor.giveInitialCardsTo(this.players)
    }

    fun playGame(onHitBlock: ((Player) -> Unit)? = null): PlayerRecords {
        this.players.forEach { player ->
            player.hitWhileWants(cardDistributor, onHitBlock)
        }
        return PlayerRecords.of(dealer, playerBets)
    }
}
