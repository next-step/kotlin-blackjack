package view

import Hand
import card.PlayingCard
import card.Suit
import participant.Player

class OutputView {
    fun printScore(player: Player) {
        print(MESSAGE_PLAYER_CARD.format(player.name, player.state.hand.toDisplay()))
        println(MESSAGE_SCORE.format(player.state.hand.score()))
    }

    fun printPlayerCards(player: Player) {
        println(MESSAGE_PLAYER_CARD.format(player.name, player.state.hand.toDisplay()))
    }

    fun printFirstTurn(players: List<Player>) {
        println(MESSAGE_DEALING_CARDS.format(players.joinToString { it.name }))
        players.forEach { printPlayerCards(it) }
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
        private const val MESSAGE_DEALING_CARDS = "Dealing two cards to %s"
        private const val MESSAGE_PLAYER_CARD = "%s's cards: %s"
        private const val MESSAGE_SCORE = "– Total: %d"
    }
}
