package com.nextstep.blackjack.view

import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.domain.Players

class OutputView {
    fun printAfterDealing(players: Players) {
        val playerNames = players.names().joinToString(", ")
        println("\n${playerNames}에게 2장의 나누었습니다.")
    }

    fun printStatus(player: Players) {
        player.players.forEach {
            println("${it.name}카드: ${it.cards.joinToString(separator = ", ")}")
        }
    }

    fun printResult(players: List<Player>) {
        println("pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21")
        TODO()
    }
}
