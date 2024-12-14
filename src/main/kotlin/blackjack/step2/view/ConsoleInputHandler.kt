package blackjack.step2.view

import blackjack.step2.domain.Bet
import blackjack.step2.domain.Player

object ConsoleInputHandler {
    fun inputPlayerNames(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",").map { it.trim() }.filter { it.isNotBlank() }
        return names.map { Player(it) }
    }

    fun inputBetAmounts(players: List<Player>): List<Bet> {
        return players.map { player ->
            val bet = this.inputBettingAmount(player)
            Bet(player.name, bet)
        }
    }

    private fun inputBettingAmount(player: Player): Int {
        println("${player.name}의 배팅 금액은?")
        return readln().toIntOrNull() ?: inputBettingAmount(player)
    }
}
