package view

import Hand
import Player
import PlayingCard
import Suit

class OutputView {
    fun printScore(player: Player) {
        print(MESSAGE_PLAYER_CARD.format(player.name, player.hand.toDisplay()))
        println(MESSAGE_SCORE.format(player.hand.score()))
    }

    fun printPlayerCards(player: Player) {
        println(MESSAGE_PLAYER_CARD.format(player.name, player.hand.toDisplay()))
    }

    private fun Hand.toDisplay(): String {
        return this.cards.map { it.toDisplay() }.toString()
    }

    private fun PlayingCard.toDisplay(): String {
        return this.denomination.toString() + this.suit.toEmoji()
    }

    private fun Suit.toEmoji(): String {
        return when (this) {
            Suit.CLUB -> "♣️"
            Suit.DIAMOND -> "♦️"
            Suit.HEART -> "❤️"
            Suit.SPADE -> "♠️"
        }
    }

    companion object {
        private const val MESSAGE_PLAYER_CARD = "%s's cards: %s"
        private const val MESSAGE_SCORE = "– Total: %d"
    }
}
