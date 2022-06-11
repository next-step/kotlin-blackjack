package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.player.Player

object PlayersReader {

    fun read(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames = requireNotNull(readLine()).split(',').map { it.trim() }

        check(playerNames.isNotEmpty()) { "플레이어를 입력해주세요." }

        val players = mutableListOf<Player>()

        for (playerName in playerNames) {
            check(playerName.isNotBlank()) { "올바른 플레이어 이름을 입력해주세요. ($playerName)" }
            players.add(Player(playerName))
        }

        return players
    }
}
