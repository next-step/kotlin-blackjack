package blackjack.domain.player

import blackjack.domain.model.BlackJackErrorCode

@JvmInline
value class Players(private val players: List<Player>) : List<Player> by players {

    init {
        check(value = size in LIMIT_PLAYERS) {
            BlackJackErrorCode.CAN_NOT_PARTICIPATE_RANGE_OF_PLAYERS.message(
                arrayOf(LIMIT_PLAYERS, size)
            )
        }
    }

    companion object {
        private val LIMIT_PLAYERS: IntRange = 2..8
    }
}
