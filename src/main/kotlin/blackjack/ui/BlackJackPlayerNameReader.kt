package blackjack.ui

import blackjack.domain.player.PlayerName

object BlackJackPlayerNameReader {

    const val DELEMITER = ","
    fun read(): List<PlayerName> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(DELEMITER)
            .map { it.trim() }
            .map { PlayerName.from(it) }
    }
}
