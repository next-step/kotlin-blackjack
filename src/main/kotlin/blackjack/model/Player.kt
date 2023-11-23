package blackjack.model

import blackjack.model.pack.Pack
import blackjack.view.InputView
import blackjack.view.OutputView

class Player(
    val name: String,
    val cards: Cards = Cards.emptyCards(),
) : Playable {
    fun deal(pack: Pack) {
        cards.add(pack.pickCard())
        cards.add(pack.pickCard())
    }

    fun hit(pack: Pack) {
        cards.add(pack.pickCard())
    }

    override fun score(): Int {
        return this.cards.totalScore()
    }

    fun playing(pack: Pack) {
        if (InputView.askHit(this)) {
            this.hit(pack)
        }
        OutputView.playerCardPresent(this)
    }
}
