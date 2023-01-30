package blackjack.domain

class Gamer(
    override val name: String,
    override val ownCards: OwnCards = OwnCards(Draw.FIRST_DRAW_COUNT),
    override val isBlackJack: Boolean = false,
    private var _state: State = State.DRAW
) : Person() {

    val state: State
        get() = _state

    fun changeState(dealer: Dealer) {
        val dealerCardSum = dealer.ownCards.sumCardNumber()
        val gamerCardSum = ownCards.sumCardNumber()
        _state =
            if (dealerCardSum > Draw.DRAW_LIMIT || ((gamerCardSum < Draw.DRAW_LIMIT) && gamerCardSum > dealerCardSum)) State.WIN
            else if (gamerCardSum == dealerCardSum) State.DRAW
            else if (isBlackJack) State.BLACKJACK
            else State.LOSE
    }
}
