package card

class CardPack(val cardList: List<PlayingCard>) {

    companion object {
        private val cardPack = createCard()

        private fun createCard(): CardPack {
            val suitList = Suit.getSuitList()
            val playingCardList = mutableListOf<PlayingCard>()

            for (suit in suitList) {
                playingCardList.addAll(createCardWithSuit(suit))
            }

            cardShuffle(playingCardList)

            return CardPack(playingCardList)
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

        fun getCardPack(): CardPack {
            return cardPack
        }
    }
}
