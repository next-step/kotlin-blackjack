package blackjack.ui

import blackjack.Player
import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.NormalCard
import blackjack.card.PictureCard

class OutputManager {

    fun printFirstTurn(players: List<Player>) {
        val names: String = players.joinToString(", ") { it.name }

        println("${names}에게 두장의 카드를 나누었습니다.")
    }

    fun printPlayersCards(players: List<Player>) {
        players.forEach {
            println("${it.name}: ${parsingCardsToString(it.cards)}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}: ${parsingCardsToString(player.cards)}")
    }

    fun printPlayerResultGame(player: Player, score: Int) {
        println("${player.name} 카드: ${parsingCardsToString(player.cards)} - 결과: $score")
    }

    private fun parsingCardsToString(cards: List<BlackJackCard>): String {
        return cards.joinToString(", ") { parsingCardToString(it) }
    }

    private fun parsingCardToString(card: BlackJackCard): String {
        return when (card) {
            is NormalCard -> "${card.number}${card.pattern.patternName}"
            is PictureCard -> "${card.picture.pictureName}${card.pattern.patternName}"
            is AceCard -> "ace${card.pattern.patternName}"
        }
    }
}
