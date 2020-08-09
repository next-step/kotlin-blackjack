package blackjack

object ResultView {

    fun printPreGame(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.map { printPlayerHaveDeck(it) }
    }

    fun printPlayerHaveDeck(player: Player) {
        println("${player.name}카드: ${player.myReceivedDeck.joinToString()}")
    }
}
