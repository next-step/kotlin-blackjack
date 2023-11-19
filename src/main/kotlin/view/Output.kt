package view

import blackjack.Card
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER
import blackjack.GamePlayer
import blackjack.GamePlayers
import blackjack.Message
import blackjack.Message.PRINT_CONTINUE_DEAL

object Output {

    fun printMessage(message: String) {
        println(message)
    }

    fun printPlayerAction(player: GamePlayer) {
        println(PRINT_CONTINUE_DEAL.format(player.name))
    }

    fun printPlayerCards(player: GamePlayer) {
        println(Message.PRINT_PLAYER_CARDS.format(player.name, getCardsName(player.cards)))
    }

    fun printPlayersResult(players: GamePlayers) {
        players.players.forEach {
            println(Message.PRINT_PLAYER_RESULT.format(it.name, getCardsName(it.cards), it.getScore()))
        }
    }

    fun printPlayersInitialDealing(playing: GamePlayers) {
        val names = getPlayerNames(playing)
        println(Message.PRINT_DEAL_CARDS.format(names))

        playing.players.forEach(::printPlayerCards)
    }

    private fun getPlayerNames(players: GamePlayers): String =
        players.players.joinToString(PLAYER_NAME_DELIMITER) { it.name }

    private fun getCardsName(cards: List<Card>): String =
        cards.joinToString(PLAYER_NAME_DELIMITER) { "${it.number.print}${it.symbol.kor}" }
}
