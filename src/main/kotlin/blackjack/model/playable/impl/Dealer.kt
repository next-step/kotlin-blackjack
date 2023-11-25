package blackjack.model.playable.impl

import blackjack.model.Players
import blackjack.model.Referee
import blackjack.model.card.Cards
import blackjack.model.pack.Pack
import blackjack.model.playable.Playable
import blackjack.model.playable.PlayableReaction
import blackjack.model.playable.PlayableResult
import blackjack.model.playblestrategy.PlayingStrategy
import blackjack.model.result.DealerResult

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

    override fun result(playable: Playable): PlayableResult {
        TODO("Not yet implemented")
    }

    override fun isBurst(): Boolean {
        return this.score() > Referee.BLACK_JACK_SCORE
    }

    fun dealerResult(players: Players): Pair<Dealer, DealerResult> {
        val results = players.values.map { it -> this.result(it) }
        return this to DealerResult(
            score = this.score(),
            winningCount = results.count { it == PlayableResult.WIN },
            drawingCount = results.count { it == PlayableResult.DRAW },
            losingCount = results.count { it == PlayableResult.LOSE }
        )
    }
}
