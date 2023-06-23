package blackjack.domain

import blackjack.HandsCalculator

class Hand {

    private val hands = mutableListOf<PokerCard>()

    fun hands(): List<PokerCard> {
        return hands.toList()
    }

    fun toRepresent(): String {
        return hands.joinToString(transform = PokerCard::representCard)
    }

    fun optimalValue(): Int {
        return HandsCalculator.calculateOptimalValue(hands)
    }

    fun addNewCard(pokerCard: PokerCard) {
        hands.add(pokerCard)
    }

    fun isBlackJackOrBust(): Boolean {
        return HandsCalculator.calculateOptimalValue(hands) >= BLACK_JACK_NUMBER
    }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
    }
}
