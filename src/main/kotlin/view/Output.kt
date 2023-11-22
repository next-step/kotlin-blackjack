package view

import blackjack.Card
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER
import blackjack.GameParticipant
import blackjack.GameParticipantPlayerResult
import blackjack.GameParticipantResults
import blackjack.GameParticipants
import blackjack.MatchResult
import blackjack.Message
import blackjack.Message.PRINT_CONTINUE_DEAL
import blackjack.Message.PRINT_DEALER_RESULT_MESSAGE
import blackjack.Message.PRINT_PLAYER_RESULT_MESSAGE
import blackjack.Message.PRINT_RESULT_MESSAGE

object Output {

    fun printMessage(message: String) {
        println(message)
    }

    fun printParticipantAction(participant: GameParticipant) {
        println(PRINT_CONTINUE_DEAL.format(participant.name))
    }

    fun printParticipantCards(participant: GameParticipant) {
        println(Message.PRINT_PLAYER_CARDS.format(participant.name, getCardsName(participant.cards)))
    }

    fun printParticipantResult(players: GameParticipants) {
        players.participants.forEach {
            println(Message.PRINT_PLAYER_RESULT.format(it.name, getCardsName(it.cards), it.getScore()))
        }
    }

    fun printParticipantsInitialDealing(playing: GameParticipants) {
        val names = getParticipantsNames(playing)
        println(Message.PRINT_DEAL_CARDS.format(names))

        playing.participants.forEach(::printParticipantCards)
    }

    private fun getParticipantsNames(players: GameParticipants): String =
        players.participants.joinToString(PLAYER_NAME_DELIMITER) { it.name }

    private fun getCardsName(cards: List<Card>): String =
        cards.joinToString(PLAYER_NAME_DELIMITER) { "${it.number.print}${it.symbol.kor}" }

    fun printGameParticipantResults(gameParticipantResults: GameParticipantResults) {
        println(PRINT_RESULT_MESSAGE)
        printParticipantResults(gameParticipantResults.players)
    }

    private fun printParticipantResults(players: List<GameParticipantPlayerResult>) {
        var win = 0
        var loss = 0

        val playersMessage = players.joinToString("\n") {
            if (it.matchResult == MatchResult.WIN) {
                loss++
            } else {
                win++
            }
            PRINT_PLAYER_RESULT_MESSAGE.format(it.name, it.matchResult.message)
        }
        val dealerMessage = PRINT_DEALER_RESULT_MESSAGE.format(GameParticipant.Dealer.NAME, win, loss)
        println(dealerMessage)
        printMessage(playersMessage)
    }
}
