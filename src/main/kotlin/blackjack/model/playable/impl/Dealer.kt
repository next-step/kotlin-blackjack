package blackjack.model.playable.impl

import blackjack.model.Players
import blackjack.model.card.Cards
import blackjack.model.pack.Pack
import blackjack.model.playable.BlackjackScore
import blackjack.model.playable.Playable
import blackjack.model.playable.PlayableReaction
import blackjack.model.playable.PlayableResult
import blackjack.model.playblestrategy.PlayingStrategy
import blackjack.model.result.DealerResult

class Dealer(
    val cards: Cards = Cards(),
) : Playable {

    override fun score(): BlackjackScore {
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

    override fun result(other: Playable): PlayableResult {
        return this.score() vs other.score()
    }

    override fun isBurst(): Boolean {
        return this.score().isBurst()
    }

    fun dealerResult(players: Players): DealerResult {
        return DealerResult.of(players.values.map { this.result(it) }, this.score())
    }
}
