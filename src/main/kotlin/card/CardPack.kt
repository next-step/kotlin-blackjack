package card

class CardPack private constructor(private val playingCard: List<PlayingCard>) {

    companion object {

        private val cardPack = createCard()

        private fun createCard(): List<PlayingCard> {
            val suitList = Suit.getSuitList()
            val playingCardList = mutableListOf<PlayingCard>()

            for (suit in suitList) {
                playingCardList.addAll(createCardWithSuit(suit))
            }

            return playingCardList
        }

        private fun createCardWithSuit(suit: Suit): List<PlayingCard> {
            val playingCardList = mutableListOf<PlayingCard>()
            val cardNumberList = CardNumber.getCardNumberList()

            for (cardNumber in cardNumberList) {
                playingCardList.add(PlayingCard(suit, cardNumber))
            }
            return playingCardList
        }

        fun getCradPack() = cardPack
    }
}
