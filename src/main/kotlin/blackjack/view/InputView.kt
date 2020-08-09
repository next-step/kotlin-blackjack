package blackjack.view

import blackjack.model.Player

object InputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun getPlayer(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val names = readLine().toString().split(PLAYER_NAME_DELIMITER)

        return names.map { Player(it) }
    }
}
