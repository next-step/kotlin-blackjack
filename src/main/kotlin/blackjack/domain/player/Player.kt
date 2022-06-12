package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.ReceivedCards
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
        get() = calculateScore()

    val name: String
        get() = _name

    val receivedCards: ReceivedCards
        get() = _receivedCards

    val gamblingSummary: GamblingSummary
        get() = _gamblingSummary

    fun calculateScore(): Int {
        var score = receivedCards.sumOfCards()

        if (score > BLACKJACK_SCORE) {
            val aceCount = receivedCards.countOfAceCard()

            score = score - (ACE_NUMBER_TO_ELEVEN * aceCount) + (ACE_NUMBER_TO_ONE * aceCount)
        }

        return score
    }

    fun canMoreGame(): Boolean {
        return calculateScore() < BLACKJACK_SCORE
    }

    fun addCard(card: Card) {
        receivedCards.addCard(card)
    }

    fun wantToTake(takeMorePlayerStrategy: TakeMorePlayerStrategy): Boolean {
        return takeMorePlayerStrategy.wantToTake(this)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_NUMBER_TO_ONE = 1
        private const val ACE_NUMBER_TO_ELEVEN = 11
    }
}
