package blackjack.domain.participant

import blackjack.domain.bettingmoney.BettingMoney
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.gameresult.GameResult
import blackjack.domain.gameresult.GameResults

sealed class Participant(
    val name: String,
    val cards: Cards = Cards(),
    var bettingMoney: BettingMoney = BettingMoney()
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

    abstract fun isAbleToDraw(): Boolean

    abstract fun openInitCards(): List<Card>
}

private const val DEALER_NAME = "딜러"
private const val DEALER_DRAW_STANDARD = 16

class Dealer(
    cards: Cards = Cards()
) : Participant(
    name = DEALER_NAME,
    cards
) {
    override fun isAbleToDraw(): Boolean {
        return cards.calculateScore() <= DEALER_DRAW_STANDARD
    }

    override fun openInitCards(): List<Card> {
        return listOf(cards.value.first())
    }

    fun getDealerResult(players: List<Player>): GameResults {
        return players.groupingBy { !GameResult.valueOf(it.score, score) }
            .eachCount()
            .let { GameResults(name, it) }
    }

    fun decideWinOrLoseResults(players: List<Player>): List<GameResults> {
        return players.map { GameResults(it.name, GameResult.valueOf(it.score, score)) }
    }
}

private const val PLAYER_MAX_SCORE = 21
private const val PLAYER_OPEN_INIT_CARDS_LENGTH = 2

class Player(
    name: String,
    cards: Cards = Cards(),
    bettingMoney: BettingMoney = BettingMoney()
) : Participant(
    name,
    cards,
    bettingMoney,
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
