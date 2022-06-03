package blackjack.ui

import blackjack.domain.Player

object InputView {

    tailrec fun readPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",")
        return when (names.size >= 2) {
            true -> names
            false -> {
                println("블랙잭을 진행하기 위해선 최소 2명의 플레이어가 필요합니다")
                return readPlayerNames()
            }
        }
    }

    tailrec fun askHit(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> {
                println("y 또는 n을 입력해 주세요")
                askHit(player)
            }
        }
    }
}
