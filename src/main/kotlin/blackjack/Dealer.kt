package blackjack

class Dealer : User(NAME) {

    override fun firstDeal(cardExtractor: CardExtractor) {
        super.firstDeal(cardExtractor)

        if (cardDeck.getScore() <= STAND_RANGE) {
            addCard(cardExtractor.getCard())
        }
    }

    override fun getEvaluate(users: Users): Int {
        TODO("Not yet implemented")
    }

    override fun getFirstDeal(): List<Card> {
        return listOf(cardDeck.cards[0])
    }

    companion object {
        const val NAME = "딜러"
        const val STAND_RANGE = 16
    }
}
