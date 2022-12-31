package blackjack.domain

import blackjack.domain.Finished.Blackjack
import blackjack.domain.Finished.Stay
import blackjack.model.Bet
import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import blackjack.model.PlayerProfit
import java.util.UUID

class FakePlayer(
    override val name: String = UUID.randomUUID().toString(),
    override val play: Play = FakeGamePlay(),
    override val bet: Bet = Bet(1000),
    private val profit: Double = 0.0
) : Player {
    override fun profit(dealer: Dealer): PlayerProfit.Player = PlayerProfit.Player(name, profit)
}

class FakeDealer(
    override val name: String = "딜러",
    override val deck: CardDeck = GameCardDeck(),
    override val play: Play = FakeGamePlay(),
    private val deliveredCard: Card = Card(CardType.ACE, CardShape.CLOVER),
    private val profit: Double = 0.0
) : Dealer {

    override fun shuffle() {
        // do nothing
    }

    override fun deliverCard(): Card = deliveredCard

    override fun profit(gamePlayerProfits: List<PlayerProfit.Player>): PlayerProfit.Dealer =
        PlayerProfit.Dealer(name, profit)
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
    private val profit: Double? = null,
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
    override fun profit(bet: Int): Double = profit ?: when {
        blackjack -> Blackjack().profit(bet)
        stay -> Stay().profit(bet)
        else -> 0.0
    }
}
