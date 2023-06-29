package blackjack.view

import blackjack.domain.game.Money
import blackjack.domain.game.PlayerAnswer
import blackjack.domain.gamer.PlayerInitProperty

class BlackJackInputView {

    fun readPlayerInitProperties(): List<PlayerInitProperty> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
            .split(",")
            .map { playerName -> PlayerInitProperty(playerName, readBetAmount(playerName)) }
    }

    fun readHitAnswer(playerName: String): PlayerAnswer {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln().lowercase()) {
            "y" -> PlayerAnswer.HIT
            "n" -> PlayerAnswer.STAY
            else -> throw IllegalArgumentException("wrong input. please input y or n")
        }
    }

    private fun readBetAmount(playerName: String): Money {
        println("\n${playerName}의 배팅 금액은?")
        return Money(readln().toInt())
    }
}
