import card.CardPack
import card.PlayingCard
import player.Player
import player.PlayerGroup

class BlackjackGame(private val cardPack: CardPack, private val playerGroup: PlayerGroup) {

    private val index = GameIndex(maxCardIndex = cardPack.cardList.size)

    init {
        playerGroup.playerList.forEach {
            distributeTwoCards(it)
        }
    }

    fun hit(): PlayingCard {
        val card = cardPack.cardList[index.cardIndex]
        increaseCardIndex()
        return card
    }

    fun savePlayerCard(playingCard: PlayingCard) {
        playerGroup.getCurrentPlayer().saveCard(playingCard)
    }

    private fun distributeTwoCards(player: Player) {
        repeat(2) {
            player.saveCard(hit())
        }
    }

    private fun increaseCardIndex() {
        index.increaseCardIndex()
    }

    companion object {
        private const val MIN_PLAYER_CNT = 2
    }
}
