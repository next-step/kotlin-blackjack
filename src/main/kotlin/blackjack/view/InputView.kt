package blackjack.view

import blackjack.model.player.Player
import blackjack.model.player.PlayerName

interface InputView {
    fun printPlayerNamesInputMessage()

    fun inputPlayerNames(): List<Player>

    fun printNeedMoreCardAskMessage(name: PlayerName)

    fun inputWhetherNeedMoreCard(): String
}
