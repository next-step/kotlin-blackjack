import card.CardPack
import card.PlayingCard
import player.Player

class BlackjackGame(private val cardPack: CardPack, private val playerList: List<Player>) {

    private val index = CardIndex(maxIndex = cardPack.cardList.size)

    init {
        validateCardPack()
        validatePlayer()
    }

    fun hit(): PlayingCard {
        val card = cardPack.cardList[index.getIndex()]
        increaseCardIndex()
        return card
    }

    private fun validateCardPack() {
        validateCardPackSize()
        validateDuplicateCardPack()
    }

    private fun validateCardPackSize() {
        require(cardPack.cardList.size == CARD_PACK_SIZE) { ERR_MSG_CARD_PACK_SIZE }
    }

    private fun validateDuplicateCardPack() {
        require(cardPack.cardList.size == cardPack.cardList.distinct().size) { ERR_MSG_DUPLICATE_CARD_PACK }
    }

    private fun validatePlayer() {
        require(playerList.size in MIN_PLAYER_CNT..MAX_PLAYER_CNT) { "참여 가능한 플레이어의 범위를 넘어섰습니다." }
    }

    private fun increaseCardIndex() {
        index.increaseIndex()
    }

    companion object {
        private const val CARD_PACK_SIZE = 52
        private const val ERR_MSG_CARD_PACK_SIZE = "카드는 52개로 구성되어야 합나다."
        private const val ERR_MSG_DUPLICATE_CARD_PACK = "중복되는 카드는 없어야 합니다."
        private const val MIN_PLAYER_CNT = 2
        private const val MAX_PLAYER_CNT = CARD_PACK_SIZE / 2
    }
}
