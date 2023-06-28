package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player

object BlackJackPlayerReader {

    const val DELEMITER = ","

    fun read(dealer: Dealer): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readln().split(DELEMITER)
            .map { it.trim() }
            .map { Player.of(it, dealer.open()) }

        val playerNames = players.map { it.name }
        println("${playerNames.joinToString(separator = ", ")}에게 2장의 나누었습니다.")
        return players
    }
}
