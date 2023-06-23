package blackjack.view

import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerNames

class BlackJackInputView {

    fun readPlayerNames(): PlayerNames {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",").map { PlayerName(it) }
        return PlayerNames(names)
    }
}
