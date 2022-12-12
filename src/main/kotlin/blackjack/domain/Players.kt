package blackjack.domain

class Players(val player: List<Player>) {

    companion object {
        const val MIN_NUMBER_PLAYERS = 2
        const val MAX_NUMBER_PLAYERS = 8
    }
}
