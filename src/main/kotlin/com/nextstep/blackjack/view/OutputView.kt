package com.nextstep.blackjack.view

import com.nextstep.blackjack.domain.Player

object OutputView {
    fun printStartMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printInitialStateMessage(players: List<Player>) {
        val sb = StringBuilder()
        val playerNames = players.map { it.name }
        sb.append("${playerNames.joinToString(", ")}에게 2장의 카드를 나누었습니다.\n")
        players.forEach {
            sb.append("${it.name}카드: ${it.getCards().joinToString(", ")}\n")
        }
        println(sb)
    }

    fun printOngoingMessage(name: String) {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printPlayerOngoingStatusMessage(player: Player) {
        println("${player.name}카드: ${player.getCards().joinToString(", ")}")
    }

    fun printPlayerStatusMessage(players: List<Player>) {
        println()
        for (player in players) {
            println("${player.name}카드: ${player.getCards().joinToString(", ")} - 결과: ${player.calculateScore()}")
        }
    }
}
