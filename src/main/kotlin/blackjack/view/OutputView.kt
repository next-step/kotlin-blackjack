

package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.card.Cards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.domain.participant.forEach

object OutputView {

    private const val DRAW_CARD_MESSAGE_FORMAT = "%s와 %s에게 %d장의 카드를 나누었습니다."
    private const val PARTICIPANT_RESULT_MESSAGE_FORMAT = "%s: %s"
    private const val BUST_MESSAGE = "%s는 점수 총합 %d으로, 버스트 되었습니다."
    private const val CARD_RESULT_MESSAGE = "%s카드: %s - 결과: %d"
    private const val DEALER_RECEIVE_CARD_MESSAGE = "%s는 %d이하라 한장의 카드를 더 받았습니다."
    private const val GAME_RESULT_MESSAGE_FORMAT = "## 최종 승패"

    fun drawCardMessage(dealer: Dealer, players: Players, initDrawCardCount: Int) {
        val dealerName = dealer.name()
        val playerNames = players.values.joinToString(", ") { it.name() }
        println(createDrawCardMessage(dealerName, playerNames, initDrawCardCount))
    }

    fun playerCardMessage(participant: Participant) {
        val name = participant.name()
        val cards = if (participant is Dealer) participant.cards().first() else participant.cards()
        val cardMessage = convertPlayerCardMessage(cards)
        val message = String.format(
            PARTICIPANT_RESULT_MESSAGE_FORMAT,
            if (participant is Dealer) name else "${name}카드",
            cardMessage,
        )
        println(message)
    }

    fun bustMessage(player: Player) {
        println(String.format(BUST_MESSAGE, player.name(), player.calculateScore().value))
    }

    fun printResult(dealer: Dealer, players: Players) {
        print(System.lineSeparator())
        printParticipantResult(dealer)
        players.forEach { player ->
            printParticipantResult(player)
        }
    }

    fun printDealerReceiveCardMessage(dealer: Dealer, minScore: Int) {
        println(System.lineSeparator() + String.format(DEALER_RECEIVE_CARD_MESSAGE, dealer.name(), minScore))
    }

    fun printGameResult(gameResult: GameResult) {
        println(System.lineSeparator() + GAME_RESULT_MESSAGE_FORMAT)
        val dealerResultMessage = createDealerResultMessage(gameResult)
        println(String.format(PARTICIPANT_RESULT_MESSAGE_FORMAT, gameResult.dealerName, dealerResultMessage))
        gameResult.playerResults.forEach {
            println(createPlayerResultMessage(it))
        }
    }

    private fun createDrawCardMessage(dealerName: String, playerNames: String, initDrawCardCount: Int): String {
        return String.format(
            System.lineSeparator() + DRAW_CARD_MESSAGE_FORMAT,
            dealerName,
            playerNames,
            initDrawCardCount
        )
    }

    private fun convertPlayerCardMessage(cards: Cards): String {
        return cards.values.joinToString(", ") {
            CardViewCreator.convert(it)
        }
    }

    private fun printParticipantResult(participant: Participant) {
        val playerName = participant.name()
        val playerCards = convertPlayerCardMessage(participant.cards())
        val playerScore = participant.calculateScore().value
        println(String.format(CARD_RESULT_MESSAGE, playerName, playerCards, playerScore))
    }

    private fun createDealerResultMessage(gameResult: GameResult): String {
        return "${gameResult.dealerWinCount}${GameResultMessage.WIN.message} " +
            "${gameResult.dealerDrawCount}${GameResultMessage.DRAW.message} " +
            "${gameResult.dealerLoseCount}${GameResultMessage.LOSE.message}"
    }

    private fun createPlayerResultMessage(result: GameResult.GameResultByPlayer): String {
        return String.format(
            PARTICIPANT_RESULT_MESSAGE_FORMAT,
            result.playerName,
            GameResultMessage.convert(result.result)
        )
    }

    enum class GameResultMessage(val message: String) {
        WIN("승"),
        DRAW("무"),
        LOSE("패")
        ;

        companion object {
            fun convert(gameResult: GameResult.Result): String {
                return when (gameResult) {
                    GameResult.Result.WIN -> WIN.message
                    GameResult.Result.DRAW -> DRAW.message
                    GameResult.Result.LOSE -> LOSE.message
                }
            }
        }
    }
}
