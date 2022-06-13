package blackjack.model

import blackjack.model.player.Player
import blackjack.model.player.PlayerRecords
import blackjack.model.player.Players
import blackjack.model.player.Players.Companion.toPlayers
import blackjack.model.player.RecordCalculator

class PlayRoom(
    val cardDistributor: CardDistributor,
    val dealer: Player.Dealer,
    val guests: Players
) {

    private val players: Players by lazy {
        this.guests.toMutableList()
            .apply { this.add(dealer) }
            .toPlayers()
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
        return RecordCalculator(dealer, guests, cardDistributor.initialCardCountForEachPlayer).calculate()
    }
}
