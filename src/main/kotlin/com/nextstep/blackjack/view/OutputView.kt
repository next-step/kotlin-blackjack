package com.nextstep.blackjack.view

import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.domain.Players

class OutputView {
    fun printAfterDealing(players: Players) {
        val playerNames = players.names().joinToString(", ")
        println("\n${playerNames}에게 2장의 나누었습니다.")
    }

    fun printStatus(vararg players: Player) {
        players.forEach {
            println(createStatusMessage(it))
        }
        println()
    }

    fun printResult(vararg players: Player) {
        println()
        players.forEach {
            println(createStatusMessage(it) + " - 결과: ${it.calculateScore()}")
        }
    }

    private fun createStatusMessage(player: Player) = "${player.name} 카드: ${player.cards.joinToString(separator = ", ")}"
}
