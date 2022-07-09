package game.blackjack.view

import game.blackjack.domain.Card
import game.blackjack.domain.Dealer
import game.blackjack.domain.Dealer.Companion.CAN_RECEIVE_SCORE
import game.blackjack.domain.Denomination
import game.blackjack.domain.Player
import game.blackjack.domain.Players
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

    fun printAllPlayerCard(players: Players) {
        println("\n딜러와 ${players.players.joinToString { it.name }}에게 ${players.players[0].hand.size()}장의 카드를 나누었습니다.")
        players.forEachWithDealer {
            when (it) {
                is Dealer -> println("${it.name}카드: ${formatCard(it.hand.cards().first())}")
                else -> println(formatPlayerCard(it))
            }
        }
    }

    fun printPlayerCard(player: Player) {
        when (player) {
            is Dealer -> println("\n딜러는 ${CAN_RECEIVE_SCORE.toInt()}이하라 한장의 카드를 더 받았습니다.")
            else -> println(formatPlayerCard(player))
        }
    }

    private fun formatPlayerCard(player: Player) = "${player.name}카드: ${formatCards(player.hand.cards())}"

    fun printScore(players: Players) {
        println()
        players.forEachWithDealer {
            println(
                "${it.name}카드: ${formatCards(it.hand.cards())} - 결과: ${it.hand.score().toInt()}"
            )
        }
    }

    fun printResult(players: Players) {
        println("\n## 최종 수익")
        val result = players.getResult()
        println("딜러: ${result.values.sumOf { it * -1 }}")
        players.players.forEach { println("${it.name}: ${result[it.name]}") }
    }

    private fun formatCards(cards: List<Card>): String = cards.joinToString { formatCard(it) }

    private fun formatCard(card: Card): String = "${denominationSymbols[card.denomination]} ${suitSymbols[card.suit]}"
}
