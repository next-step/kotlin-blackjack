package blackjack.view

import blackjack.domain.Nickname
import blackjack.domain.PlayerDecision

object InputView {

    private const val NICKNAME_DELIMITER = ","

    fun readNicknames(): List<Nickname> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(NICKNAME_DELIMITER).map { Nickname(it.trim()) }
    }

    fun readHitOrStand(nickname: Nickname): PlayerDecision {
        println("${nickname.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> PlayerDecision.HIT
            "n" -> PlayerDecision.STAND
            else -> throw IllegalArgumentException("y 또는 n만 입력 가능합니다.")
        }
    }
}
