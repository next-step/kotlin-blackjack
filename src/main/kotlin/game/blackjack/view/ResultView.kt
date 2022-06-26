package game.blackjack.view

import game.blackjack.domain.Card
import game.blackjack.domain.Denomination
import game.blackjack.domain.Player
import game.blackjack.domain.Suit

class ResultView {

    private val denominationSymbols: Map<Denomination, String> = Denomination.values().associateWith { toSymbol(it) }

    private val suitSymbols: Map<Suit, String> = mapOf(
        Suit.SPADE to "♠",
        Suit.DIAMOND to "◆",
        Suit.HEART to "♥",
        Suit.CLOVER to "♣",
    )

    private fun toSymbol(denomination: Denomination): String =
        when (denomination) {
            Denomination.ACE -> "A"
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
            else -> denomination.score.toInt().toString()
        }

    fun printAllPlayerCard(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 ${players[0].cards.size()}장의 카드를 나누었습니다.")
        players.forEach { printPlayerCard(it) }
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${formatCards(player.cards.get())}")
    }

    fun printResult(players: List<Player>) {
        players.forEach { println("${it.name}카드: ${formatCards(it.cards.get())} - 결과: ${Card.score(it.cards.get()).toInt()}") }
    }

    private fun formatCards(cards: List<Card>): String = cards.joinToString { formatCard(it) }

    private fun formatCard(card: Card): String = "${denominationSymbols[card.denomination]} ${suitSymbols[card.suit]}"
}
