import card.CardPack
import player.Player
import player.PlayerGroup
import player.Status

class BlackjackGame(private val cardPack: CardPack, private val playerGroup: PlayerGroup) {

    init {
        playerGroup.playerList.forEach {
            distributeTwoCards(it)
        }
    }

    fun hitCard() {
        val player = playerGroup.getCurrentPlayer()
        player.saveCard(cardPack.hit())
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
            player.saveCard(cardPack.hit())
        }
    }
}
