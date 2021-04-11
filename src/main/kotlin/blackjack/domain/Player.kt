package blackjack.domain

import blackjack.domain.state.State
import blackjack.domain.state.notstarted.NotStarted

class Player(
    val name: String,
    private val price: Int
) : Participant {

    var state: State = NotStarted()

    private val cards = mutableSetOf<Card>() //TODO: Delete
    val cardNames: List<String>
        get() = cards.map { it.toString() }
    val cardSize: Int
        get() = cards.size
    override val isBlackjack: Boolean //TODO: Delete
        get() = _isBlackjack
    private var _isBlackjack: Boolean = false //TODO: Delete

    constructor(name: String, cards: Set<Card>, isBlackjack: Boolean = false) : this(name) {
        this.cards.addAll(cards)
        _isBlackjack = isBlackjack
    }

    constructor(name: String, price: Int, cards: Set<Card>, isBlackjack: Boolean = false) : this(name, price) {
        this.cards.addAll(cards)
        _isBlackjack = isBlackjack
    }

    constructor(name: String) : this(name, 0)

    override fun takeCard(card: Card) {
        state.takeCard(card)
    }

    override fun takeFirstTwoCards(card1: Card, card2: Card) {
        state.takeFirstTwoCards(Cards(listOf(card1, card2)))
    }

    override fun cardPointSum(): Int {
        return state.cardPointSum()
    }

    //TODO: delete
    fun profit(dealerPoint: PlayerPoint): Profit {
        val playerPoint = PlayerPoint(this.cardPointSum(), this.isBlackjack)
        val playerWinType = PlayerWinType.findPlayerWinType(playerPoint, dealerPoint)
        val profitAmount = playerWinType.calculateProfit(this.price)
        return Profit(this.name, profitAmount)
    }

    fun profit(dealerState: State): Profit {
        val profitAmount = state.profit(price, dealerState)
        return Profit(this.name, profitAmount)
    }

    companion object {
        const val BLACK_JACK_TWENTY_ONE = 21
    }
}
