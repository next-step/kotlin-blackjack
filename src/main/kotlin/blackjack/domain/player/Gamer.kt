package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Score

abstract class Gamer(
    val name: PlayerName,
    val hand: Hand = Hand.createEmpty(),
    private val callback: AfterHitWhileCallback = AfterHitWhileCallback {}
) {

    protected abstract fun wantHit(answerProvider: AnswerProvider): Boolean

    protected abstract fun firstOpenCardsCount(): Int

    fun hitWhileWant(deck: Deck, answerProvider: AnswerProvider) {
        while (wantHit(answerProvider)) {
            hit(deck)
            callback.onAfterHit(this)
        }
    }

    fun firstOpenCards() = hand.cards.take(firstOpenCardsCount())

    val score: Score
        get() = hand.score

    fun hit(deck: Deck) {
        check(canHit()) { "카드를 뽑을 수 없습니다." }

        hand.add(deck.drawCard())
    }

    fun canHit() = wantHit { PlayerAnswer.YES }
}

fun interface AfterHitWhileCallback {
    fun onAfterHit(gamer: Gamer)
}
