package blackjack.domain

import blackjack.service.PlayerCardAdditionDecider
import blackjack.view.input.ConsoleInputView.Companion.NUMBER_OF_PLAYER_SHOULD_BE_LARGER_THAN_ZERO
import blackjack.view.input.ConsoleInputView.Companion.PLAYER_NAME_DELIMITER

data class Players(val items: List<Player>) {
    init {
        require(items.isNotEmpty()) { NUMBER_OF_PLAYER_SHOULD_BE_LARGER_THAN_ZERO }
    }

    operator fun get(index: Int): Player = items[index]

    fun getNames(): String {
        return items.joinToString { it.name }
    }

    companion object {
        fun from(input: String): Players {
            return Players(buildPlayer(input.split(PLAYER_NAME_DELIMITER)))
        }

        fun buildPlayer(names: List<String>) = names
            .map {
                Player(
                    it.trim(),
                    PlayerCardsHandler(PlayerCards(), PlayerCardAdditionDecider())
                )
            }
            .toList()
    }
}
