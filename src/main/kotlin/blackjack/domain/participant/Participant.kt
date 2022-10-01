package blackjack.domain.participant

import blackjack.domain.GameProfit.GameProfit
import blackjack.domain.bettingmoney.BettingMoney
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.gameresult.GameResult
import java.math.BigDecimal

sealed class Participant(
    val name: String,
    val cards: Cards = Cards(),
    var bettingMoney: BettingMoney = BettingMoney.ZERO,
) {
    val score: Int
        get() = cards.calculateScore()

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 빈칸 혹은 여백을 허용하지 않습니다. name = $name" }
    }

    fun drawCard(card: Card) {
        this.cards.add(card)
    }

    fun drawCards(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun isBlackjack(): Boolean = cards.isBlackjack

    abstract fun isAbleToDraw(): Boolean

    abstract fun openInitCards(): List<Card>
}

private const val DEALER_NAME = "딜러"
private const val DEALER_DRAW_STANDARD = 16

class Dealer(
    cards: Cards = Cards()
) : Participant(
    name = DEALER_NAME,
    cards = cards,
    bettingMoney = BettingMoney.ZERO
) {
    override fun isAbleToDraw(): Boolean {
        return cards.calculateScore() <= DEALER_DRAW_STANDARD
    }

    override fun openInitCards(): List<Card> {
        return listOf(cards.value.first())
    }

    // TODO 2022-10-01 경록: 테스트 코드 추가 작성
    fun getDealerResult(players: List<Player>): GameResult {
        val gameProfits = players.map { !calculatePlayerGameProfit(it) }
        return GameResult(name, gameProfits)
    }

    // TODO 2022-10-01 경록: 테스트 코드 추가 작성
    fun decideWinOrLoseResults(players: List<Player>): List<GameResult> {
        return players.map { GameResult(it.name, calculatePlayerGameProfit(it)) }
    }

    private fun calculatePlayerGameProfit(player: Player): GameProfit {
        if (isDealerWin(player)) {
            return !GameProfit(player.bettingMoney.value)
        }

        if (isPlayerWin(player)) {
            return getPlayerProfit(player)
        }

        return GameProfit.NONE
    }

    private fun isDealerWin(player: Player): Boolean {
        if (player.score > GameProfit.SCORE_LIMIT) {
            return true
        }

        if (this.score > GameProfit.SCORE_LIMIT || this.score <= player.score) {
            return false
        }

        return true
    }

    private fun isPlayerWin(player: Player): Boolean {
        val playerScore = player.score
        if (playerScore > GameProfit.SCORE_LIMIT) {
            return false
        }

        if (this.score > GameProfit.SCORE_LIMIT || this.score < playerScore) {
            return true
        }

        return false
    }

    private fun getPlayerProfit(player: Player): GameProfit {
        if (player.isBlackjack()) {
            return decidePlayerProfitWhenPlayerIsBlackjack(player)
        }

        return GameProfit(player.bettingMoney.value)
    }

    private fun decidePlayerProfitWhenPlayerIsBlackjack(player: Player): GameProfit {
        if (isBlackjack()) {
            return GameProfit.NONE
        }

        return GameProfit(player.bettingMoney.value * GameProfit.BLACKJACK_EARNING_RATE)
    }
}

private const val PLAYER_MAX_SCORE = 21
private const val PLAYER_OPEN_INIT_CARDS_LENGTH = 2

class Player(
    name: String,
    cards: Cards = Cards(),
    bettingMoney: BettingMoney = BettingMoney.ZERO
) : Participant(
    name = name,
    cards = cards,
    bettingMoney = bettingMoney
) {
    private var turn: Boolean = true

    override fun isAbleToDraw(): Boolean {
        return turn && score < PLAYER_MAX_SCORE
    }

    override fun openInitCards(): List<Card> {
        return cards.value.subList(0, PLAYER_OPEN_INIT_CARDS_LENGTH)
    }

    fun endOwnTurn() {
        this.turn = false
    }
}

private operator fun BigDecimal.times(multiplier: Double): BigDecimal {
    return this.multiply(BigDecimal.valueOf(multiplier))
}
