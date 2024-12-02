package blackjack.domain

import kotlin.math.absoluteValue

data class Player(
    val name: String,
) {
    val deck = PlayerCardDeck()

    fun addCard(card: Card) {
        deck.addCard(card)
    }

    fun findEnabledMoreCard(): Boolean {
        val possibleScores = deck.findPossibleCardScores()
        return !possibleScores.contains(BLACK_JACK_NUMBER) && possibleScores.any { it < BLACK_JACK_NUMBER }
    }

    fun findClosestToBlackJackNumber() = deck.findPossibleCardScores().minBy { (BLACK_JACK_NUMBER - it).absoluteValue }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
    }
}
