package blackjack

class Dealer : User(NAME) {

    override fun firstDeal(cardExtractor: CardExtractor) {
        super.firstDeal(cardExtractor)

        if (cardDeck.getScore() <= STAND_RANGE) {
            addCard(cardExtractor.getCard())
        }
    }

    companion object {
        const val NAME = "딜러"
        const val STAND_RANGE = 16
    }
}
