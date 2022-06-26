package blackjack.view

import blackjack.domain.Player

object InputView {

    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val players = readln().split(",").map { it.trim() }
        require(players.isNotEmpty()) { "플레이어는 1명 이상이어야 합니다." }

        return players
    }

    fun inputWillDraw(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return when (readln().trim().lowercase()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n만 입력하실 수 있습니다..")
        }
    }
}

object OutputView {

    fun printPlayers(players: List<Player>) {
        players.forEach { printPlayer(it) }
    }

    fun printPlayer(player: Player) {
        println("${player.name}카드: " + player.hand.joinToString { card -> "${card.cardNumber.symbol}${card.cardSuit.suit}" })
    }

    fun printOutput(results: GameResults) {
        results.result.forEach {
            println("${it.name}카드: ${it.hand.joinToString { card -> "${card.cardNumber}${card.cardSuit}" }} - 결과: ${it.score}")
        }
    }
}
