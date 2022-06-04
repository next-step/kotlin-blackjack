package com.nextstep.jngcii.blackjack.view

import com.nextstep.jngcii.blackjack.INITIAL_COUNT
import com.nextstep.jngcii.blackjack.domain.PlayerBoard

object ResultView {
    private const val JOIN_COMMA = ", "

    fun printReady(boards: List<PlayerBoard>) {
        val names = boards.joinToString(JOIN_COMMA) { it.playerName }
        println("${names}에게 카드 ${INITIAL_COUNT}장을 나누어 주었습니다.")
    }

    fun printPlayerState(board: PlayerBoard) {
        val name = board.playerName
        val cards = board.cards.joinToString(JOIN_COMMA) { "${it.symbol}-${it.shape}" }

        println("${name}카드 : $cards")
    }

    fun printResult(boards: List<PlayerBoard>) {
        boards.forEach {
            printPlayerState(it)
            println(" ㄴ 결과 : ${it.total}")
        }
    }
}
