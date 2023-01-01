package com.nextstep.blackjack.view

import com.nextstep.blackjack.domain.Player

class OutputView {
    fun printAfterDealing(players: List<Player>) {
        println("pobi, jason에게 2장의 나누었습니다.")
        TODO()
    }

    fun printStatus(players: List<Player>) {
        players.forEach {
            println("${it.name}카드: 2하트, 8스페이드")
        }
        TODO()
    }

    fun printResult(players: List<Player>) {
        println("pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21")
        TODO()
    }
}
