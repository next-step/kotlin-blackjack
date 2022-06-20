package blackjack.view

import blackjack.common.PlayerDecision
import blackjack.common.PlayerProperty

interface InputView {
    fun getPlayerProperties(): List<PlayerProperty>

    fun getPlayerDecision(playerName: String): PlayerDecision
}
