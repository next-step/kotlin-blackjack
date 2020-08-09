package blackjack

object ResultView {

    fun printPreGame(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        repeat(players.size) {
            println("${players[it].name}카드: ${players[it].myReceivedDeck.first()}, ${players[it].myReceivedDeck.last()}")
        }
    }
}
