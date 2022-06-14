package blackjack.view

import blackjack.domain.common.Money
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus

object InputView {
    fun players(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames = readln().split(",").also { println() }
        return playerNames.map {
            Player(name = it, batting = readBatting(it))
        }
    }

    private fun readBatting(name: String): Money {
        return try {
            println("${name}의 배팅 금액은?")
            Money.of(readln().toInt()).also { println() }
        } catch (e: Exception) {
            println("잘못된 배팅 금액입니다. 다시 입력해주세요.")
            readBatting(name)
        }
    }

    fun isHit(player: Player): PlayerStatus {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> PlayerStatus.HIT
            "n" -> PlayerStatus.STAY
            else -> {
                println("예는 y, 아니오는 n으로 입력해주세요.")
                isHit(player)
            }
        }
    }
}
