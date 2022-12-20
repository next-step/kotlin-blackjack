package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import java.util.UUID

class FakePlayer(
    override val name: String = UUID.randomUUID().toString(),
    override val cards: Cards = Cards(),
    private val hit: Boolean = false,
    private val sumCards: Int = 0,
    private val bust: Boolean = false,
    private val blackjack: Boolean = false,
) : Player {

    override fun readyToPlay(initialCards: List<Card>) {
        // do nothing
    }

    override fun hit(card: Card): Boolean = hit

    override fun sumCards(): Int = sumCards

    override fun bust(): Boolean = bust

    override fun blackjack(): Boolean = blackjack
}

class FakeDealer(
    override val name: String = "딜러",
    override val cards: Cards = Cards(),
    private val deliveredCard: Card = Card(CardType.ACE, CardShape.CLOVER),
    private val stay: Boolean = false,
    private val hit: Boolean = false,
    private val sumCards: Int = 0,
    private val bust: Boolean = false,
    private val blackjack: Boolean = false,
) : Dealer {
    override val deck: CardDeck
        get() = CardDeckImpl()

    override fun shuffle() {
        // do nothing
    }

    override fun deliverCard(): Card = deliveredCard

    override fun stay(): Boolean = stay

    override fun readyToPlay(initialCards: List<Card>) {
        // do nothing
    }

    override fun hit(card: Card): Boolean = hit

    override fun sumCards(): Int = sumCards

    override fun bust(): Boolean = bust

    override fun blackjack(): Boolean = blackjack
}
