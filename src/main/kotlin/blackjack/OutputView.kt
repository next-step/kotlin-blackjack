package blackjack

object OutputView {
    fun printPlayersStartCardPack(players: List<Player>) {
        println("\n${players.joinToString { it.name.value }}에게 2장의 카드를 나누었습니다.")
        players.forEach { println("$it") }
    }
}