package blackjack.domain.player

import blackjack.domain.behavior.State
import blackjack.domain.card.Card
import blackjack.domain.model.BlackJackErrorCode

private typealias DrawingEvent = () -> Card

class Player(val name: String, state: State) {

    var state: State = state
        private set

    init {
        require(value = name.length in NAME_LENGTH_RANGE) {
            BlackJackErrorCode.CAN_NOT_USED_RANGE_OF_NAME_LENGTH.message(
                arrayOf(NAME_LENGTH_RANGE, name)
            )
        }
    }

    companion object {
        private val NAME_LENGTH_RANGE: IntRange = 1..10
    }
}
