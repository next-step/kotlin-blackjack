package blackjack.model

import blackjack.model.player.Player
import blackjack.model.player.PlayerRecords
import blackjack.model.player.Players
import blackjack.model.player.Players.Companion.toPlayers

class PlayRoom(
    private val cardDistributor: CardDistributor,
    val dealer: Player.Dealer,
    val guests: Players,
    private val initialCardCountOfPlayer: Int
) {

    private val players: Players by lazy {
        this.guests.toMutableList()
            .apply { this.add(dealer) }
            .toPlayers()
    }

    fun startNewGame() {
        this.cardDistributor.resetCardSet()
        this.players.clearCard()
        this.cardDistributor.giveCardsTo(this.players, initialCardCountOfPlayer)
    }

    fun playGame(onHitBlock: ((Player) -> Unit)? = null): PlayerRecords {
        this.players.forEach { player ->
            player.hitWhileWants(cardDistributor, onHitBlock)
        }
        return PlayerRecords.of(dealer, guests)
    }
}
