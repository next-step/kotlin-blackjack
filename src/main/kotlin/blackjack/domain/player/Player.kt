package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.ReceivedCards
import blackjack.domain.game.strategy.TakeMorePlayerStrategy

open class Player(
    private val _name: String,
    private val _receivedCards: ReceivedCards = ReceivedCards(mutableSetOf()),
    private val _gambleSummary: GambleSummary = GambleSummary()
) {
    constructor(name: String, cards: List<Card>) : this(_name = name) {
        cards.map {
            _receivedCards.addCard(it)
        }
    }

    val score: Int
        get() = _receivedCards.calculateScore().score

    val name: String
        get() = _name

    val receivedCards: ReceivedCards
        get() = _receivedCards

    val gambleSummary: GambleSummary
        get() = _gambleSummary

    fun canMoreGame(): Boolean {
        return _receivedCards.calculateScore().score < BLACKJACK_SCORE
    }

    fun isBust(): Boolean {
        return _receivedCards.calculateScore().score > BLACKJACK_SCORE
    }

    fun isBlackJack(): Boolean {
        return _receivedCards.count() == CARD_SIZE_FOR_BLACKJACK && _receivedCards.calculateScore().score == BLACKJACK_SCORE
    }

    fun adjustBustBattingAmount() {
        _gambleSummary.battingAmount = _gambleSummary.battingAmount.unaryMinus()
    }

    fun addCard(card: Card) {
        _receivedCards.addCard(card)
    }

    fun wantToTake(takeMorePlayerStrategy: TakeMorePlayerStrategy): Boolean {
        return takeMorePlayerStrategy.wantToTake(this)
    }

    fun isDraw(player: Player): Boolean {
        return player.score == this.score
    }

    fun isWin(compare: Player): Boolean {
        if (isBust()) {
            return false
        }

        return compare.score > BLACKJACK_SCORE || compare.score < score || score == BLACKJACK_SCORE
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        const val CARD_SIZE_FOR_BLACKJACK = 2
        const val INIT_PICK_CARD_NUMBER = 2
    }
}
