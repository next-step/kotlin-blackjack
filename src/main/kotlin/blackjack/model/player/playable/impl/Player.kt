package blackjack.model.player.playable.impl

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.blackjack.judgment.impl.BlackJackJudgment
import blackjack.model.card.Cards
import blackjack.model.card.pack.Pack
import blackjack.model.player.BlackjackScore
import blackjack.model.player.PlayableReaction
import blackjack.model.player.playable.Playable
import blackjack.model.player.playblestrategy.PlayingStrategy
import blackjack.model.result.PlayableResult

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
        return BlackJackJudgment.sentence(this, playable)
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
