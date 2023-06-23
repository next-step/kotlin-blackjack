package blackjack.domain

import blackjack.domain.Game.Companion.INIT_TAKE_SIZE
import kotlin.math.abs

class Dealer(deck: Deck = Deck()) :
    Player(
        deck = deck.copy(),
        name = DEFAULT_DEALER_NAME
    ),
    GameOutcomeCalculator {
    override fun addCard(card: Card) {
        require(isAddable()) {
            "현재 점수가 $THRESHOLD 이상이거나 카드를 분배받은 적이 있는 경우 추가할 수 없습니다."
        }.run { super.addCard(card) }
    }

    override fun isAddable(): Boolean = deck.score() <= THRESHOLD && deck.size <= INIT_TAKE_SIZE

    override fun calculate(players: Players): GameResult {
        val dealerRecord = players.groupingBy(::recodingOutcome)
            .eachCount()
            .mapKeys { it.key.dealerOutcome }

        return GameResult(
            dealerRecord = dealerRecord,
            playerRecords = players.associateWith { recodingOutcome(it).playerOutcome }
        )
    }

    private fun recodingOutcome(player: Player): OutcomeResultEntry {
        val dealerScore = calculateScore()
        val playerScore = player.calculateScore()

        return when {
            dealerScore > Game.THRESHOLD -> PLAYER_WIN_PAIR
            playerScore > Game.THRESHOLD -> DEALER_WIN_PAIR
            (abs(Game.THRESHOLD - dealerScore) < abs(Game.THRESHOLD - playerScore)) -> DEALER_WIN_PAIR
            else -> PLAYER_WIN_PAIR
        }
    }

    companion object {
        const val THRESHOLD = 16
        const val DEFAULT_DEALER_NAME = "딜러"
        private val DEALER_WIN_PAIR =
            OutcomeResultEntry(dealerOutcome = OutcomeType.WIN, playerOutcome = OutcomeType.LOSE)
        private val PLAYER_WIN_PAIR =
            OutcomeResultEntry(dealerOutcome = OutcomeType.LOSE, playerOutcome = OutcomeType.WIN)

        data class OutcomeResultEntry(val dealerOutcome: OutcomeType, val playerOutcome: OutcomeType)
    }
}
