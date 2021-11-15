package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Score

abstract class Gamer(val name: PlayerName, val hand: Hand = Hand.createEmpty()) {

    abstract fun canHit(): Boolean

    abstract fun firstOpenCardsCount(): Int

    val score: Score
        get() = hand.score

    fun hit(deck: Deck) {
        check(canHit()) { "카드를 뽑을 수 없습니다." }

        hand.add(deck.drawCard())
    }
}
