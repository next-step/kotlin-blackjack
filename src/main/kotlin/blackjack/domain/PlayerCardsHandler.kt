package blackjack.domain

class PlayerCardsHandler(
    private val cards: ParticipantCards,
    private val cardAdditionDecider: ParticipantCardAdditionDecider,
    private val resultCalculator: ResultCalculator
) : ParticipantCardsHandler {
    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun canReceiveAdditionalCard(): Boolean {
        return cardAdditionDecider.canReceiveAdditionalCard(resultCalculator.getSumOfMinimumCardValues(cards))
    }

    override fun getCards(): List<Card> {
        return cards.cards
    }

    override fun getCardsString(): String {
        return cards.toString()
    }

    override fun getCardsResultPoint(): Int {
        return resultCalculator.getCardsResultPoint(cards)
    }
}
