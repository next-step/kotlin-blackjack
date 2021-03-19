package blackjack

object ResultView {
    fun prepare(players: Players) {
        val names = players.joinToString(", ") { it.name }
        println("${names}에게 2장의 나누었습니다.")
        for (player in players) {
            println("${player.name}카드: ${player.cards.names()}")
        }
    }

    fun result(players: Players) {
        println()
        for (player in players.allPlayers()) {
            println("${player.name}카드: ${player.cards.names()} - 결과: ${player.score()}")
        }
    }

    fun List<Card>.names(): String = joinToString { card -> "${card.name}${card.symbol.displayName}" }
}
