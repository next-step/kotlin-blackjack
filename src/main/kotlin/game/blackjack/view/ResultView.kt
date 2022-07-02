package game.blackjack.view

import game.blackjack.domain.Card
import game.blackjack.domain.Dealer
import game.blackjack.domain.Dealer.Companion.CAN_RECEIVE_SCORE
import game.blackjack.domain.Denomination
import game.blackjack.domain.Player
import game.blackjack.domain.Players
import game.blackjack.domain.Suit
import game.blackjack.domain.WinningRecord

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
        println("\n딜러와 ${players.players.joinToString { it.name }}에게 ${players.players[0].cards.size()}장의 카드를 나누었습니다.")
        players.forEachWithDealer {
            when (it) {
                is Dealer -> println("${it.name}카드: ${formatCard(it.cards.get().first())}")
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

    private fun formatPlayerCard(player: Player) = "${player.name}카드: ${formatCards(player.cards.get())}"

    fun printScore(players: Players) {
        println()
        players.forEachWithDealer { println("${it.name}카드: ${formatCards(it.cards.get())} - 결과: ${it.cards.score().toInt()}") }
    }

    fun printResult(players: Players) {
        println("\n## 최종 승패")

        val result = players.getResult()
        val eachCount = result.values.groupingBy { it }.eachCount()
        println("딜러:" +
            (eachCount[WinningRecord.LOSE]?.let { " ${it}승" } ?: "") +
            (eachCount[WinningRecord.WIN]?.let { " ${it}패" } ?: "") +
            (eachCount[WinningRecord.TIE]?.let { " ${it}무" } ?: "")
        )
        players.players.forEach { println("${it.name}: ${convertRecord(result[it.name]!!)}") }
    }

    private fun formatCards(cards: List<Card>): String = cards.joinToString { formatCard(it) }

    private fun formatCard(card: Card): String = "${denominationSymbols[card.denomination]} ${suitSymbols[card.suit]}"

    private fun convertRecord(record: WinningRecord): String = when(record) {
        WinningRecord.WIN -> "승"
        WinningRecord.LOSE -> "패"
        WinningRecord.TIE -> "무"
    }
}
