package blackjack.domain.player

import blackjack.domain.model.BlackjackErrorCode

@JvmInline
value class PlayerName(val name: String) {

    init {
        require(value = name.length in NAME_LENGTH_RANGE) {
            BlackjackErrorCode.CAN_NOT_USED_RANGE_OF_NAME_LENGTH.message(
                arrayOf(NAME_LENGTH_RANGE, name)
            )
        }
    }

    companion object {
        private val NAME_LENGTH_RANGE: IntRange = 1..10
    }
}
