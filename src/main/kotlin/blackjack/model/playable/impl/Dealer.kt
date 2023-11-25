package blackjack.model.playable.impl

import blackjack.model.card.Cards
import blackjack.model.pack.Pack
import blackjack.model.playable.Playable
import blackjack.model.playable.PlayableReaction
import blackjack.model.playblestrategy.PlayingStrategy
import blackjack.model.result.DealerResult
import blackjack.model.result.PlayersResults

class Dealer(
    val cards: Cards = Cards(),
) : Playable {

    override fun score(): Int {
        return cards.totalScore()
    }

    override fun dealing(pack: Pack) {
        this.cards.add(pack.pickCard())
        this.cards.add(pack.pickCard())
    }

    fun countOfCards(): Int {
        return cards.count()
    }

    override fun playing(playingStrategy: PlayingStrategy, pack: Pack): PlayableReaction {
        if (playingStrategy.isHit()) {
            cards.add(pack.pickCard())
            return PlayableReaction.HIT
        }
        return PlayableReaction.STAND
    }

    fun result(playerResults: PlayersResults): Pair<Dealer, DealerResult> {
        return this to DealerResult(
            score = this.score(),
            dealerWinningCount = playerResults.loseCount(),
            dealerDrawingCount = playerResults.drawingCount(),
            dealerLosingCount = playerResults.winningCount()
        )
    }

    companion object {
        private const val DEALER_PICK_THRESHOLD = 16
    }
}
