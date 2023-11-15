package view

import blackjack.Card
import blackjack.GamePlayer
import blackjack.GamePlayers
import blackjack.GamePlayers.Companion.PLAYER_NAME_DELIMITER
import blackjack.Message

object Output {

    fun printMessage(message: String) {
        println(message)
    }

    fun printPlayers(players: GamePlayers) {
        val names = getPlayerNames(players)
        println(Message.PRINT_DEAL_CARDS.format(names))

        val cards = getPlayerCards(players)
        println(cards)
    }

    private fun getPlayerCards(players: GamePlayers): String =
        players.players.joinToString("\n") {
            Message.PRINT_PLAYER_CARDS.format(it.name, getCardsName(it.cards))
        }

    private fun getCardsName(cards: List<Card>): String =
        cards.joinToString(PLAYER_NAME_DELIMITER) { "${it.number.value}${it.symbol.kor}" }

    private fun getPlayerNames(players: GamePlayers): String =
        players.players.joinToString(PLAYER_NAME_DELIMITER) { it.name }
}
