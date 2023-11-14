package blackjack.model

import blackjack.controller.BlackJackGame.Companion.BEST_SCORE

data class Participant(
    val name: String,
    val cards: MutableList<Card> = mutableListOf(),
) {
    fun isPossibleToTakeMoreCard(): Boolean {
        return checkCurrentScore() < BEST_SCORE
    }

    private fun checkCurrentScore(): Int {
        return cards.sumOf { it.info.value1 }
    }
}
