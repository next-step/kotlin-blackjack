package blackjack.model.player.playable.impl

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.player.Players
import blackjack.model.card.Cards
import blackjack.model.card.pack.Pack
import blackjack.model.player.BlackjackScore
import blackjack.model.player.playable.Playable
import blackjack.model.player.PlayableReaction
import blackjack.model.result.PlayableResult
import blackjack.model.player.playblestrategy.PlayingStrategy
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

    override fun result(playable: Playable): PlayableResult {
        if (this.isBurst() && playable.isBurst()) {
            return PlayableResult.DRAW
        }
        if (this.isBurst() && !playable.isBurst()) {
            return PlayableResult.LOSE
        }
        if (!this.isBurst() && playable.isBurst()) {
            return PlayableResult.WIN
        }
        return this.score() vs playable.score()
    }

    override fun status(): BlackJackStatus {
        if (this.score().isBurst()) {
            return BlackJackStatus.DIE
        }
        return BlackJackStatus.ALIVE
    }

    override fun isBurst(): Boolean {
        return this.score().isBurst()
    }

    fun dealerResult(players: Players): DealerResult {
        return DealerResult.of(players.values.map { this.result(it) }, this.score())
    }

    fun isNotBurst(): Boolean {
        return !this.isBurst()
    }
}
