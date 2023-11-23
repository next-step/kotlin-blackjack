package blackjack.model

import blackjack.model.pack.Pack

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
