package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import java.util.UUID

class FakePlayer(
    override val name: String = UUID.randomUUID().toString(),
    override val play: Play = FakeGamePlay(),
) : Player

class FakeDealer(
    override val name: String = "딜러",
    override val deck: CardDeck = GameCardDeck(),
    override val play: Play = FakeGamePlay(),
    private val deliveredCard: Card = Card(CardType.ACE, CardShape.CLOVER),
) : Dealer {

    override fun shuffle() {
        // do nothing
    }

    override fun deliverCard(): Card = deliveredCard
}

class FakeGamePlay(
    private val state: State = Started(),
    private val _finished: Boolean = false,
    private val _hit: Boolean = false,
    private val _stay: Boolean = false,
    private val _bust: Boolean = false,
    private val _blackjack: Boolean = false,
    private val readyToPlay: Boolean = false,
    private val score: Int = 0,
) : Play {
    override val finished: Boolean
        get() = _finished
    override val hit: Boolean
        get() = _hit
    override val stay: Boolean
        get() = _stay
    override val bust: Boolean
        get() = _bust
    override val blackjack: Boolean
        get() = _blackjack

    override val cards: Cards
        get() = state.cards

    override fun shouldBeReadyToPlay(): Boolean = readyToPlay

    override fun draw(card: Card) {
        // do nothing
    }

    override fun stay() {
        // do nothing
    }

    override fun score(): Int = score
}
