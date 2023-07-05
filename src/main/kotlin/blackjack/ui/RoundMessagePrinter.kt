package blackjack.ui

import blackjack.domain.player.PlayerName

object RoundMessagePrinter {
    fun open(playerNames: List<PlayerName>) {
        val playerNameSeparatedByComma = playerNames.map { it.value }.joinToString(separator = ", ")
        println("딜러와 ${playerNameSeparatedByComma}에게 2장의 나누었습니다.")
    }


}


