import card.deck.GameDeck
import player.Player
import player.PlayerGroup
import player.Status

class BlackjackGame(private val cardDeck: GameDeck, private val playerGroup: PlayerGroup) {

    init {
        playerGroup.playerList.forEach {
            distributeTwoCards(it)
        }
    }

    fun hit() {
        val player = playerGroup.getCurrentPlayer()
        player.saveCard(cardDeck.getCardWithIncrease())
        player.updateStatus()
    }

    fun getCurrentPlayer(): Player {
        return playerGroup.getCurrentPlayer()
    }

    // 턴오버.
    fun playerTurnOver() {
        val player = playerGroup.getCurrentPlayer()
        if (player.status == Status.STAND) {
            playerGroup.turnOverPlayer()
        }
    }

    fun hitDone() {
        val player = playerGroup.getCurrentPlayer()
        player.playDone()
    }

    private fun distributeTwoCards(player: Player) {
        repeat(2) {
            player.saveCard(cardDeck.getCardWithIncrease())
        }
    }
}
