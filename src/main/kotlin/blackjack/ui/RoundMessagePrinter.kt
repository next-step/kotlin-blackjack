package blackjack.ui

import blackjack.domain.PlayerName

object RoundMessagePrinter {
    fun open(playerNames: List<PlayerName>) {
        val playerNameSeparatedByComma = playerNames.map { it.value }.joinToString(separator = ", ")
        println("딜러와 ${playerNameSeparatedByComma}에게 2장의 나누었습니다.")
    }

    fun hitOrStay(playerName: PlayerName): RoundAnswer {
        println("${playerName.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return RoundAnswer.valueOf(readln())
    }
}

enum class RoundAnswer { y, n }
