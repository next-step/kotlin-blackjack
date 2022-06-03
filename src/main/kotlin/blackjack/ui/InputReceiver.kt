package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players

object InputReceiver {

    fun receiverPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().replace(" ", "").split(",")
        return Players(names.map(::Player))
    }
}
