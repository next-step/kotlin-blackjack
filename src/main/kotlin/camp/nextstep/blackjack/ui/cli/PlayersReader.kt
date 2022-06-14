package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.player.Player

object PlayersReader {

    fun read(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames = readln().split(',').map { it.trim() }

        check(playerNames.isNotEmpty()) { "플레이어를 입력해주세요." }

        return playerNames.map { Player(it) }
    }
}
