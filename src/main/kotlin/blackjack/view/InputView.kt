package blackjack.view

import blackjack.domain.Player

object InputView {
    private const val INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DELIMITER = ","

    tailrec fun getPlayers(): List<String> {
        println(INPUT_PLAYERS)
        return readLine()
            ?.split(DELIMITER)
            ?.takeIf { it.isNotEmpty() }
            ?: getPlayers()
    }

    tailrec fun getMoreCard(it: Player): String {
        println("${it.name}님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readLine()
            ?: getMoreCard(it)
    }
}
