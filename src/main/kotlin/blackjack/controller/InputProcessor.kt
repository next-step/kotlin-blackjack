package blackjack.controller

import blackjack.domain.Action
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames

interface InputProcessor {
    fun playerNames(): PlayerNames

    fun playerAction(player: Player): Action
}
