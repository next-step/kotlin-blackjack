package blackjack

object ResultView {
    fun prepare(players: Players) {
        val names = players.joinToString(", ") { it.name }
        println("\n${names}에게 2장의 나누었습니다.")
        for (player in players) {
            println("${player.name}카드: ${player.cards.names()}")
        }
        println()
    }

    fun result(players: Players, bettings: List<Bet>) {
        println()
        for (player in players.allPlayers()) {
            println("${player.name}카드: ${player.cards.names()} - 결과: ${player.score()}")
        }

        val gameResult = players.gameResult()
        println("## 최종 수익")
        println("딜러: ${DealerAdjustment(gameResult, bettings).income()}")

        for (result in gameResult) {
            println("${result.name}: ${result.income(bettings)}")
        }
    }

    fun List<Card>.names(): String = joinToString { card -> "${card.name}${card.symbol.displayName}" }
}
