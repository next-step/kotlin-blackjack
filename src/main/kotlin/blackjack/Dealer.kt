package blackjack

class Dealer : User(NAME) {

    override fun firstDeal(cardExtractor: CardExtractor) {
        super.firstDeal(cardExtractor)

        if (cardDeck.getScore() <= STAND_RANGE) {
            addCard(cardExtractor.getCard())
        }
    }

    override fun getEvaluate(users: Users): Int {
        return users.getPlayers().players
            .map { it.getEvaluate(users) }
            .sum()
            .unaryMinus()
    }

    override fun getFirstDeal(): List<Card> {
        return listOf(cardDeck.cards[0])
    }

    companion object {
        const val NAME = "딜러"
        const val STAND_RANGE = 16
    }
}
