package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardType
import blackjack.domain.player.Player

class ResultView {

    fun printInitDistributed(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }} 에게 2장을 나누었습니다.")
        printCardsByPlayers(players, false)
    }

    fun printCardsByPlayers(player: List<Player>, withScore: Boolean) {
        println("")
        player.map {
            printCardsByPlayer(it, withScore)
        }
    }

    fun printCardsByPlayer(player: Player, withScore: Boolean) {
        if (withScore) {
            println("${player.name}카드: ${player.receivedCards.map { extractCardDescription(it) }} - 결과: ${player.score}")
            return
        }

        println("${player.name}카드: ${player.receivedCards.map { extractCardDescription(it) }}")
    }

    private fun extractCardDescription(card: Card): String {
        return when (card.cardType) {
            CardType.ACE, CardType.JACK, CardType.QUEEN, CardType.KING
            -> "${card.cardType.description}${card.cardSuit.description}"
            CardType.BASIC
            -> "${card.number}${card.cardSuit.description}"
        }
    }
}
