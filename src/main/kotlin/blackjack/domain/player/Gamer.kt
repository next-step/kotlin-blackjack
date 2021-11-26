package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Score
import blackjack.domain.game.GameResult
import blackjack.domain.game.Hand
import blackjack.domain.game.HandResult

abstract class Gamer(val name: PlayerName, hand: Hand) {

    var hand: Hand = hand
        private set

    protected abstract fun wantHit(answerProvider: AnswerProvider): Boolean

    protected abstract fun firstOpenCardsCount(): Int

    protected abstract val afterHitCallBack: AfterHitWhileCallback?

    val score: Score
        get() = hand.score

    val firstOpenCards: List<Card>
        get() = hand.cards.take(firstOpenCardsCount())

    val result: GameResult
        get() = hand.getResult()

    fun hit(deck: Deck) {
        if (!hand.canHit()) {
            throw IllegalStateException("카드를 뽑을 수 없습니다.")
        }

        hand = hand.hit(deck.drawCard())
    }

    fun hitWhileWant(deck: Deck, answerProvider: AnswerProvider) {
        while (hand.canHit() && wantHit(answerProvider)) {
            hit(deck)
            afterHitCallBack?.onAfterHit(this)
        }
    }
}

fun interface AfterHitWhileCallback {
    fun onAfterHit(gamer: Gamer)
}
