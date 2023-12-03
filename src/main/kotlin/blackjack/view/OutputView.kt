package blackjack.view

import blackjack.domain.card.Cards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.domain.participant.forEach

object OutputView {

    private const val DRAW_CARD_MESSAGE_FORMAT = "%s와 %s에게 %d장의 카드를 나누었습니다."
    private const val PARTICIPANT_CARD_MESSAGE_FORMAT = "%s: %s"
    private const val BUST_MESSAGE = "%s는 점수 총합 %d으로, 버스트 되었습니다."
    private const val RESULT_MESSAGE = "%s카드: %s - 결과: %d"

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
            PARTICIPANT_CARD_MESSAGE_FORMAT,
            if (participant is Dealer) name else "${name}카드",
            cardMessage,
        )
        println(message)
    }

    fun bustMessage(player: Player) {
        println(String.format(BUST_MESSAGE, player.name(), player.calculateScore().value))
    }

    fun printResult(players: Players) {
        print(System.lineSeparator())
        players.forEach { player ->
            val playerName = player.name()
            val playerCards = convertPlayerCardMessage(player.cards())
            val playerScore = player.calculateScore().value
            println(String.format(RESULT_MESSAGE, playerName, playerCards, playerScore))
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
}
