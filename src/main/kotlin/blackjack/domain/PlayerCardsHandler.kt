package blackjack.domain

import blackjack.service.ParticipantCardAdditionDecider
import blackjack.service.ResultCalculator

class PlayerCardsHandler(private val cardAdditionDecider: ParticipantCardAdditionDecider, private val resultCalculator: ResultCalculator) {
    private val cards: PlayerCards = PlayerCards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun canReceiveAdditionalCard(): Boolean {
        return cardAdditionDecider.canReceiveAdditionalCard(resultCalculator.getSumOfMinimumCardValues(cards.cards))
    }

    fun getCards(): List<Card> {
        return cards.cards.toList()
    }

    fun getCardsString(): String {
        return cards.toString()
    }

    fun getCardsResultPoint(): Int {
        return resultCalculator.getCardsResultPoint(cards.cards)
    }
}
