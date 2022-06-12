package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.ReceivedCards
import blackjack.domain.game.Score
import blackjack.domain.game.TakeMorePlayerStrategy

open class Player(
    private val _name: String,
    private val _receivedCards: ReceivedCards = ReceivedCards(mutableSetOf()),
    private val _gamblingSummary: GamblingSummary = GamblingSummary()
) {
    constructor(name: String, cardDeck: CardDeck) : this(_name = name) {
        _receivedCards.addCard(cardDeck.pickCard())
        _receivedCards.addCard(cardDeck.pickCard())
    }

    val score: Int
        get() = Score.calculateScore(_receivedCards)

    val name: String
        get() = _name

    val receivedCards: ReceivedCards
        get() = _receivedCards

    val gamblingSummary: GamblingSummary
        get() = _gamblingSummary

    fun canMoreGame(): Boolean {
        return Score.calculateScore(_receivedCards) < BLACKJACK_SCORE
    }

    fun isBust(): Boolean {
        return Score.calculateScore(_receivedCards) > BLACKJACK_SCORE
    }

    fun adjustBustBattingAmount() {
        this.gamblingSummary.battingAmount = this.gamblingSummary.battingAmount.unaryMinus()
    }

    fun addCard(card: Card) {
        receivedCards.addCard(card)
    }

    fun wantToTake(takeMorePlayerStrategy: TakeMorePlayerStrategy): Boolean {
        return takeMorePlayerStrategy.wantToTake(this)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
