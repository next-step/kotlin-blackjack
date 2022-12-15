package com.nextstep.blackjack.view

import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.domain.Players

object OutputView {
    fun printStartMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printInitialStateMessage(players: Players) {
        val sb = StringBuilder()
        val playerNames = players.players.map { it.name }
        sb.append("${playerNames.joinToString(", ")}에게 2장의 카드를 나누었습니다.\n")
        players.players.forEach { it ->
            sb.append("${it.name}카드:")
            sb.append(it.cards.joinToString(",") { it.cardNumber.number.toString() + it.symbol.symbol })
            sb.append("\n")
        }
        println(sb)
    }

    fun printOngoingMessage(name: String) {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printPlayerOngoingStatusMessage(player: Player) {
        println("${player.name}카드:")
        print(player.cards.joinToString(",") { it.cardNumber.number.toString() + it.symbol.symbol })
    }

    fun printPlayerStatusMessage(players: Players) {
        println()
        for (player in players.players) {
            print("${player.name}카드: ")
            print(player.cards.joinToString(",") { it.cardNumber.number.toString() + it.symbol.symbol })
            println(" - 결과: ${player.calculateScore()}")
        }
    }
}
