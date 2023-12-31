package blackjack.controller

import blackjack.domain.Action
import blackjack.domain.batting.BetAmount
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames

interface InputProcessor {
    fun playerNames(): PlayerNames

    fun playerBet(player: Player): BetAmount

    fun playerAction(player: Player): Action
}
