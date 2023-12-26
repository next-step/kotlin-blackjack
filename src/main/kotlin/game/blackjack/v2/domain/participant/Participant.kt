package game.blackjack.v2.domain.participant

import game.blackjack.v2.domain.Blackjack
import game.blackjack.v2.domain.card.Card

open class Participant(val name: String) {
    private val handCards: HandCards = HandCards()

    fun drawCard(card: Card) = handCards.add(card)

    fun drawCards(cards: List<Card>) = handCards.addAll(cards)

    fun isBust() = handCards.getCurrentScore() > Blackjack.WINNING_SCORE

    fun isNotBust() = !isBust()

    fun getScore() = handCards.getCurrentScore()

    fun getHandCards() = handCards.get()
}
