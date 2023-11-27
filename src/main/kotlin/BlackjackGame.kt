import card.CardPack
import card.PlayingCard
import player.Player

class BlackjackGame(private val cardPack: CardPack, private val playerList: List<Player>) {

    private val index = GameIndex(maxCardIndex = cardPack.cardList.size)

    init {
        validatePlayer()
        playerList.forEach {
            distributeTwoCards(it)
        }
    }

    fun hit(): PlayingCard {
        val card = cardPack.cardList[index.cardIndex]
        increaseCardIndex()
        return card
    }

    fun savePlayerCard(playingCard: PlayingCard) {
        playerList[index.playerIndex].saveCard(playingCard)
    }

    private fun distributeTwoCards(player: Player) {
        repeat(2) {
            player.saveCard(hit())
        }
    }

    private fun validatePlayer() {
        require(playerList.size in MIN_PLAYER_CNT..cardPack.cardList.size / 2) { "참여 가능한 플레이어의 범위를 넘어섰습니다." }
    }

    private fun increaseCardIndex() {
        index.increaseCardIndex()
    }

    companion object {
        private const val MIN_PLAYER_CNT = 2
    }
}
