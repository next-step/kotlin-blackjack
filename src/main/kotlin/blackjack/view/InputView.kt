package blackjack.view

import blackjack.common.PlayerDecision

interface InputView {
    fun getPlayerNames(): List<String>

    fun getPlayerDecision(playerName: String): PlayerDecision
}
