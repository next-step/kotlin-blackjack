import card.CardPack
import card.PlayingCard
import player.Player

class BlackjackGame(private val cardPack: CardPack, private val playerList: List<Player>) {

    private val index = GameIndex(maxIndex = cardPack.cardList.size)

    init {
        validatePlayer()
        playerList.forEach {
            initCardSetting(it)
        }
    }

    private fun initCardSetting(player: Player) {
        repeat(2) {
            player.saveCard(hit())
        }
    }

    fun hit(): PlayingCard {
        val card = cardPack.cardList[index.cardIndex]
        increaseCardIndex()
        return card
    }

    private fun validatePlayer() {
        require(playerList.size in MIN_PLAYER_CNT..cardPack.cardList.size / 2) { "참여 가능한 플레이어의 범위를 넘어섰습니다." }
    }

    private fun increaseCardIndex() {
        index.increaseIndex()
    }

    companion object {
        private const val MIN_PLAYER_CNT = 2
    }
}
