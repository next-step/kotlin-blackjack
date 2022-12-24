package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import java.util.UUID

class FakePlayer(
    override val name: String = UUID.randomUUID().toString(),
    private val _state: State = Started.Player(Cards()),
    private val _finished: Boolean = false,
    private val readyToPlay: Boolean = true,
    private val sumCards: Int = 0,
) : Player {
    override val state: State
        get() = _state

    override val cards: Cards
        get() = state.cards
    override val finished: Boolean
        get() = _finished

    override fun shouldBeReadyToPlay(): Boolean = readyToPlay

    override fun draw(card: Card) {
        TODO("Not yet implemented")
    }

    override fun stay() {
        TODO("Not yet implemented")
    }

    override fun sumCards(): Int = sumCards
}

class FakeDealer(
    override val name: String = "딜러",
    override val deck: CardDeck = GameCardDeck(),
    private val deliveredCard: Card = Card(CardType.ACE, CardShape.CLOVER),
    private val _state: State = Started.Dealer(Cards()),
    private val _finished: Boolean = false,
    private val readyToPlay: Boolean = true,
    private val stay: Boolean = false,
    private val sumCards: Int = 0,
) : Dealer {
    override val cards: Cards
        get() = state.cards
    override val state: State
        get() = TODO("Not yet implemented")
    override val finished: Boolean
        get() = TODO("Not yet implemented")

    override fun shuffle() {
        // do nothing
    }

    override fun draw(card: Card) {
        TODO("Not yet implemented")
    }

    override fun deliverCard(): Card = deliveredCard
    override fun shouldStay(): Boolean = stay

    override fun shouldBeReadyToPlay(): Boolean = readyToPlay

    override fun stay() {
        // do nothing
    }

    override fun sumCards(): Int = sumCards
}
