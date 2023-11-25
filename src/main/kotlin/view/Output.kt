package view

import blackjack.Card
import blackjack.GameBlackjack.Companion.PLAYER_NAME_DELIMITER
import blackjack.GameParticipant
import blackjack.GameParticipantDealer
import blackjack.GameParticipantPlayerResult
import blackjack.GameParticipantResults
import blackjack.GameParticipants
import blackjack.MatchResult
import blackjack.Message
import blackjack.Message.PRINT_CONTINUE_DEAL
import blackjack.Message.PRINT_PROFIT_PLAYER_RESULT_MESSAGE
import blackjack.Message.PRINT_PROFIT_RESULT_MESSAGE
import blackjack.Message.PRINT_WIN_LOSE_DEALER_RESULT_MESSAGE
import blackjack.Message.PRINT_WIN_LOSE_PLAYER_RESULT_MESSAGE
import blackjack.Message.PRINT_WIN_LOSE_RESULT_MESSAGE

object Output {

    enum class PrintResultKind {
        WIN_LOSE,
        PROFIT
    }

    fun printAny(obj: Any) {
        println(obj)
    }

    fun printParticipantAction(participant: GameParticipant) {
        printAny(PRINT_CONTINUE_DEAL.format(participant.name))
    }

    fun printParticipantCards(participant: GameParticipant) {
        printAny(Message.PRINT_PLAYER_CARDS.format(participant.name, getCardsName(participant.cards)))
    }

    fun printParticipantResult(players: GameParticipants) {
        players.participants.forEach {
            printAny(Message.PRINT_PLAYER_RESULT.format(it.name, getCardsName(it.cards), it.getScore()))
        }
    }

    fun printParticipantsInitialDealing(playing: GameParticipants) {
        val names = getParticipantsNames(playing)
        printAny(Message.PRINT_DEAL_CARDS.format(names))

        playing.participants.forEach(::printParticipantCards)
    }

    private fun getParticipantsNames(players: GameParticipants): String =
        players.participants.joinToString(PLAYER_NAME_DELIMITER) { it.name }

    private fun getCardsName(cards: List<Card>): String =
        cards.joinToString(PLAYER_NAME_DELIMITER) { "${it.number.print}${it.symbol.kor}" }

    fun printGameParticipantResults(printResultKind: PrintResultKind, gameParticipantResults: GameParticipantResults) {
        when(printResultKind) {
            PrintResultKind.WIN_LOSE -> printParticipantWinLoseResults(gameParticipantResults.players)
            PrintResultKind.PROFIT -> printParticipantProfitResults(gameParticipantResults.players)
        }
    }

    private fun printParticipantProfitResults(players: List<GameParticipantPlayerResult>) {
        printAny(PRINT_PROFIT_RESULT_MESSAGE)
        printAny(PRINT_PROFIT_PLAYER_RESULT_MESSAGE.format(GameParticipantDealer.NAME, -players.sumOf { it.profit }))
        players.map {
            printAny(PRINT_PROFIT_PLAYER_RESULT_MESSAGE.format(it.name, it.profit))
        }
    }

    private fun printParticipantWinLoseResults(players: List<GameParticipantPlayerResult>) {
        printAny(PRINT_WIN_LOSE_RESULT_MESSAGE)
        var win = 0
        var loss = 0

        val playersMessage = players.joinToString("\n") {
            if (it.matchResult == MatchResult.WIN) {
                loss++
            } else {
                win++
            }
            PRINT_WIN_LOSE_PLAYER_RESULT_MESSAGE.format(it.name, it.matchResult.message)
        }
        val dealerMessage = PRINT_WIN_LOSE_DEALER_RESULT_MESSAGE.format(GameParticipantDealer.NAME, win, loss)
        printAny(dealerMessage)
        printAny(playersMessage)
    }
}
