package blackjack.player

import blackjack.deck.Deck
import blackjack.hand.Hand

data class Player(
    val name: String,
    private val hand: Hand,
) {
    val cards
        get() = hand.cards

    fun canDrawCard(): Boolean = hand.calculateBestValue() <= 21
    fun drawCard(deck: Deck): Player {
        check(deck.isEmpty().not()) { "덱에 카드가 없으면 카드를 뽑을 수 없습니다." }
        return copy(hand = hand.addCard(deck.drawCard()))
    }
    fun calculateBestValue(): Int = hand.calculateBestValue()
}
