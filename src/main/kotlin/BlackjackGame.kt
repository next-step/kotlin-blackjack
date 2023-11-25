import card.CardPack

class BlackjackGame(private val cardPack: CardPack) {

    init {
        validateCardPack()
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

    companion object {
        private const val CARD_PACK_SIZE = 52
        private const val ERR_MSG_CARD_PACK_SIZE = "카드는 52개로 구성되어야 합나다."
        private const val ERR_MSG_DUPLICATE_CARD_PACK = "중복되는 카드는 없어야 합니다."
    }
}
