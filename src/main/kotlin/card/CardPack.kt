package card

class CardPack(val cardList: List<PlayingCard>) {

    private var cardIndex = 0

    fun hit(): PlayingCard {
        return cardPack.cardList[cardIndex++]
    }

    companion object {
        private const val CARD_PACK_SIZE = 52
        private const val ERR_MSG_CARD_PACK_SIZE = "카드는 52개로 구성되어야 합나다."
        private const val ERR_MSG_DUPLICATE_CARD_PACK = "중복되는 카드는 없어야 합니다."

        private val cardPack = createCard()

        private fun createCard(): CardPack {
            val suitList = Suit.values()
            val playingCardList = mutableListOf<PlayingCard>()

            for (suit in suitList) {
                playingCardList.addAll(createCardWithSuit(suit))
            }
            cardShuffle(playingCardList)

            val cardPack = CardPack(playingCardList)
            validateCardPack(cardPack)

            return cardPack
        }

        private fun cardShuffle(playingCardList: MutableList<PlayingCard>) {
            playingCardList.shuffle()
        }

        private fun createCardWithSuit(suit: Suit): List<PlayingCard> {
            val playingCardList = mutableListOf<PlayingCard>()
            val cardNumberList = CardNumber.getCardNumberList()

            for (cardNumber in cardNumberList) {
                playingCardList.add(PlayingCard(suit, cardNumber))
            }
            return playingCardList
        }

        private fun validateCardPack(cardPack: CardPack) {
            validateCardPackSize(cardPack)
            validateDuplicateCardPack(cardPack)
        }

        private fun validateCardPackSize(cardPack: CardPack) {
            require(cardPack.cardList.size == CARD_PACK_SIZE) { ERR_MSG_CARD_PACK_SIZE }
        }

        private fun validateDuplicateCardPack(cardPack: CardPack) {
            require(cardPack.cardList.size == cardPack.cardList.distinct().size) { ERR_MSG_DUPLICATE_CARD_PACK }
        }

        fun getCardPack(): CardPack {
            return cardPack
        }
    }
}
