package blackjack.view

import blackjack.model.Player

object InputView {
    val readPlayers: () -> List<Player> = {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        Player.playersOf(readln())
    }

    val readPlayerPickAnswer: (Player) -> String = {
        println("${it.name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        readln()
    }
}
