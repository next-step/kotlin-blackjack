package blackjack.domain

class Dealer(private val _states: MutableList<State> = mutableListOf()) : Person(name = "딜러") {

    val states: List<State>
        get() = _states

    override fun checkDrawable(): Boolean = (ownCards.sumCardNumber() <= LEAST_CARD_SUM)

    fun changeResult(gamer: List<Gamer>) {
        gamer.forEach {
            _states.add(convertGamerStateToCheckDealerState(it.state))
        }
    }

    private fun convertGamerStateToCheckDealerState(state: State): State =
        if (state == State.LOSE) State.WIN else State.LOSE

    companion object {
        const val LEAST_CARD_SUM = 16
    }
}
