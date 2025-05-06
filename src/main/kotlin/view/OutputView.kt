package view

import GameResult
import GameResult.DRAW
import GameResult.LOSE
import GameResult.WIN
import Hand
import card.PlayingCard
import card.Suit
import participant.Dealer
import participant.Participant

class OutputView {
    fun printResult(
        participant: Participant,
        result: GameResult,
    ) {
        println(MESSAGE_FINAL_RESULT)
        println(MESSAGE_RESULT.format(participant.name) + result.toDisplay())
    }

    fun printDealerResult(
        dealer: Dealer,
        getResults: Map<GameResult, Int>,
    ) {
        println(MESSAGE_RESULT.format(dealer.name) + getResults.toDisplay())
    }

    fun printDealerDrawGuide() {
        println(MESSAGE_DRAW_DEALER)
    }

    fun printScore(participant: Participant) {
        print(MESSAGE_PLAYER_CARD.format(participant.name, participant.state.hand.toDisplay()))
        println(MESSAGE_SCORE.format(participant.score()))
    }

    fun printPlayerCards(participant: Participant) {
        println(MESSAGE_PLAYER_CARD.format(participant.name, participant.showCardFirst().toDisplay()))
    }

    fun printFirstTurn(participants: List<Participant>) {
        println(MESSAGE_DEALING_CARDS.format(participants.joinToString { it.name }))
        participants.forEach { printPlayerCards(it) }
    }

    private fun List<PlayingCard>.toDisplay(): String {
        return this.map { it.toDisplay() }.toString()
    }

    private fun Hand.toDisplay(): String {
        return this.cards.map { it.toDisplay() }.toString()
    }

    private fun Map<GameResult, Int>.toDisplay(): String {
        return MESSAGE_RESULTS.format(this[WIN], this[LOSE], this[DRAW])
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

    private fun GameResult.toDisplay(): String {
        return when (this) {
            WIN -> "Win"
            LOSE -> "Lose"
            DRAW -> "Draw"
        }
    }

    companion object {
        private const val MESSAGE_DEALING_CARDS = "Dealing two cards to %s"
        private const val MESSAGE_PLAYER_CARD = "%s's cards: %s"
        private const val MESSAGE_SCORE = "– Total: %d"
        private const val MESSAGE_DRAW_DEALER = "Dealer draws one more card due to having 16 or less."
        private const val MESSAGE_FINAL_RESULT = "## Final Results"
        private const val MESSAGE_RESULT = "%s: "
        private const val MESSAGE_RESULTS = "%d Win %d Lose %d Draw"
    }
}
