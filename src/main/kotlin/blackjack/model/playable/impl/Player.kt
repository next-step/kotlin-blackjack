package blackjack.model.playable.impl

import blackjack.model.card.Cards
import blackjack.model.pack.Pack
import blackjack.model.playable.Playable
import blackjack.model.playable.PlayableReaction
import blackjack.model.playblestrategy.PlayingStrategy

class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
) : Playable {
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

    fun hit(pack: Pack) {
        cards.add(pack.pickCard())
    }

    override fun score(): Int {
        return this.cards.totalScore()
    }
}
