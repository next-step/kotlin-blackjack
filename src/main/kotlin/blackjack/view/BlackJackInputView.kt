package blackjack.view

import blackjack.domain.game.PlayerAnswer
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerNames

class BlackJackInputView {

    fun readPlayerNames(): PlayerNames {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",").map { PlayerName(it) }
        return PlayerNames(names)
    }

    fun readHitAnswer(playerName: PlayerName): PlayerAnswer {
        println("${playerName.unWrapping()}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln().lowercase()) {
            "y" -> PlayerAnswer.HIT
            "n" -> PlayerAnswer.STAY
            else -> throw IllegalArgumentException("wrong input. please input y or n")
        }
    }
}
