package com.nextstep.blackjack.view

import com.nextstep.blackjack.domain.Dealer
import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.domain.Players

object OutputView {
    fun printStartMessage() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printInitialStateMessage(dealer: Dealer, players: Players) {
        val sb = StringBuilder()
        val playerNames = players.players.map { it.name }
        sb.append("딜러와 " + "${playerNames.joinToString(", ")} 에게 2 장의 카드를 나누었습니다 .\n")
        sb.append("딜러:" + dealer.cards.joinToString(",") { it.cardNumber.number.toString() + it.symbol.symbol })
            .append("\n")
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

    fun printDealerOngoingMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printPlayerOngoingStatusMessage(player: Player) {
        print("${player.name}카드:")
        println(player.cards.joinToString(",") { it.cardNumber.number.toString() + it.symbol.symbol })
    }

    fun printStatusMessage(dealer: Dealer, players: Players) {
        println()
        print("${dealer.name}카드: ")
        print(dealer.cards.joinToString(",") { it.cardNumber.number.toString() + it.symbol.symbol })
        println(" - 결과: ${dealer.calculateScore()}")
        for (player in players.players) {
            print("${player.name}카드: ")
            print(player.cards.joinToString(",") { it.cardNumber.number.toString() + it.symbol.symbol })
            println(" - 결과: ${player.calculateScore()}")
        }
    }

    fun printResultMessage(dealer: Dealer, players: Players) {
        println()
        val dealerBeatSize = dealer.getBeatPlayer(players).size
        println("## 최종 승패")
        println("딜러: ${dealerBeatSize}승 ${players.size() - dealerBeatSize}패")
        for (player in players.players) {
            print("${player.name}: ")
            val playerResult = if (player.isBeatDealer(dealer)) "승" else "패"
            println(playerResult)
        }
    }
}
