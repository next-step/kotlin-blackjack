package blackjack.ui

import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.Players

object InputReceiver {

    fun receivePlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().replace(" ", "").split(",")
        return Players(
            names.map { name ->
                Player(name, betMoney = receiveBetMoney(name))
            }
        )
    }

    private fun receiveBetMoney(name: String): Money {
        println("${name}의 배팅 금액은?")
        return Money(readln().toInt())
    }

    fun receiveWhetherDrawCard(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().lowercase() == "y"
    }
}
