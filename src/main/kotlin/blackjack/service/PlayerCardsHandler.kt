package blackjack.service

import blackjack.domain.Card
import blackjack.domain.PlayerCards

class PlayerCardsHandler: ParticipantCardsHandler {
    private val cardAdditionDecider = PlayerCardAdditionDecider()
    private val resultCalculator = ResultCalculator()
    private val cards = PlayerCards()

    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun canReceiveAdditionalCard(): Boolean {
        return cardAdditionDecider.canReceiveAdditionalCard(resultCalculator.getSumOfMinimumCardValues(cards.cards))
    }

    override fun getCards(): List<Card> {
        return cards.cards
    }

    override fun getCardsString(): String {
        return cards.toString()
    }

    override fun getCardsResultPoint(): Int {
        return resultCalculator.getCardsResultPoint(cards.cards)
    }
}
