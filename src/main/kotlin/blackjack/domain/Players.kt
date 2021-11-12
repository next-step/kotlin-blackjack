package blackjack.domain

import blackjack.view.input.ConsoleInputView.Companion.NUMBER_OF_PLAYER_SHOULD_BE_LARGER_THAN_ZERO
import blackjack.view.input.ConsoleInputView.Companion.PLAYER_NAME_DELIMITER

data class Players(val players: List<Player>) {
    init {
        require(players.isNotEmpty()) { NUMBER_OF_PLAYER_SHOULD_BE_LARGER_THAN_ZERO }
    }

    operator fun get(index: Int): Player = players[index]

    fun getNames(): String {
        return players.joinToString { it.name }
    }

    companion object {
        fun from(input: String): Players {
            return Players(buildPlayer(input.split(PLAYER_NAME_DELIMITER)))
        }

        private fun buildPlayer(names: List<String>) = names
            .map { Player(it.trim()) }
            .toList()
    }
}
