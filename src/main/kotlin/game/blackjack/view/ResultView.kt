package game.blackjack.view

import game.blackjack.domain.Card
import game.blackjack.domain.Player

class ResultView {

    fun printAllPlayerCard(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 ${players[0].cards.size}장의 카드를 나누었습니다.")
        players.forEach { printPlayerCard(it) }
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${formatCards(player.cards)}")
    }

    fun printResult(players: List<Player>) {
        players.forEach { println("${it.name}카드: ${formatCards(it.cards)} - 결과: ${Card.score(it.cards)}") }
    }

    private fun formatCards(cards: List<Card>): String = cards.joinToString { formatCard(it) }

    private fun formatCard(card: Card): String = "${card.denomination.symbol} ${card.suit.symbol}"
}
