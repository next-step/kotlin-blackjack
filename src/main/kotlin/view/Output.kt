package view

import blackjack.Card
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER
import blackjack.GameParticipant
import blackjack.GameParticipantResult
import blackjack.GameParticipantResults
import blackjack.GameParticipants
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
        printDealerMatchResult(gameParticipantResults.dealer)
        printPlayersMatchResult(gameParticipantResults.players)
    }

    private fun printDealerMatchResult(dealer: GameParticipantResult.Dealer) {
        val (win, loss) = dealer.countByMatchResult()
        println(PRINT_DEALER_RESULT_MESSAGE.format(dealer.name, win, loss))
    }

    private fun printPlayersMatchResult(players: List<GameParticipantResult.Player>) {
        println(players.joinToString("\n") { PRINT_PLAYER_RESULT_MESSAGE.format(it.name, it.matchResult.message) })
    }
}
