package blackjack.view

import blackjack.model.Player

object InputView {
    fun readPlayers(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return Player.playersOf(readln())
    }

    fun readPlayerPickAnswer(player: Player): String {
        println("${player.name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }
}
