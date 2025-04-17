package blackjack.domain.player

import blackjack.domain.card.Card

class Players(
    val values: List<Player>,
) {
    val names = values.map { it.name.value }

    fun initializeState(block: () -> Card) {
        repeat(FIRST_TURN_REPETITIONS) {
            values.forEach { it.draw(block()) }
        }
    }

    companion object {
        private const val FIRST_TURN_REPETITIONS = 2
    }
}
