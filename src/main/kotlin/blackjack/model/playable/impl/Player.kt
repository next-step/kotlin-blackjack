package blackjack.model.playable.impl

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.card.Cards
import blackjack.model.pack.Pack
import blackjack.model.playable.BlackjackScore
import blackjack.model.playable.Playable
import blackjack.model.playable.PlayableReaction
import blackjack.model.playable.PlayableResult
import blackjack.model.playblestrategy.PlayingStrategy

class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
) : Playable {

    override fun score(): BlackjackScore {
        return this.cards.totalScore()
    }

    override fun dealing(pack: Pack) {
        cards.add(pack.pickCard())
        cards.add(pack.pickCard())
    }

    override fun playing(playingStrategy: PlayingStrategy, pack: Pack): PlayableReaction {
        if (playingStrategy.isHit()) {
            this.hit(pack)
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

    override fun isBurst(): Boolean {
        return this.score().isBurst()
    }

    fun hit(pack: Pack) {
        cards.add(pack.pickCard())
    }

    override fun status(): BlackJackStatus {
        if (this.isBurst()) {
            return BlackJackStatus.DIE
        }
        return BlackJackStatus.ALIVE
    }
}
