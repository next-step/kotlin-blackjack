package blackjack.view

import blackjack.domain.Player

object InputView {

    fun inputPlayers(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼포 기준으로 분리)")
        return readlnOrNull() ?: throw IllegalArgumentException("입력 값은 null 값이 올 수 없습니다")
    }

    fun askForCardChoice(player: Player): String {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readlnOrNull() ?: throw IllegalArgumentException("입력 값은 null 값이 올 수 없습니다")
    }
}
