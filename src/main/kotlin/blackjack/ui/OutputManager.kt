package blackjack.ui

import blackjack.Player
import blackjack.card.BlackJackCard

class OutputManager {

    fun printFirstTurn(players: List<Player>) {
        val names: String = players.map { it.name }.joinToString(", ")

        println("${names}에게 두장의 카드를 나누었습니다.")
    }

    fun printPlayersCards(players: List<Player>) {
        players.forEach {
            println("${it.name}: ${parsingCardsToString(it.cards)}")
        }
    }

    private fun parsingCardsToString(cards: List<BlackJackCard>): String {
        return cards.map { it.toString() }.joinToString(", ")
    }
}
