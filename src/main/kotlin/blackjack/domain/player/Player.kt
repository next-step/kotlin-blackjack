package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Score

class Player(val name: PlayerName, val hand: Hand = Hand.createEmpty()) {

    val score: Score
        get() = hand.score

    fun hit(deck: Deck) {
        check(canHit()) { "카드를 뽑을 수 없습니다." }

        hand.add(deck.drawCard())
    }

    fun hit(deck: Deck, answer: PlayerAnswer): DrawResult {
        if (!answer.hit) {
            return DrawResult(false)
        }
        val success = runCatching { hit(deck) }.isSuccess
        return DrawResult(success)
    }

    fun canHit(): Boolean = hand.canHit()

    @JvmInline
    value class DrawResult(val success: Boolean)
}
