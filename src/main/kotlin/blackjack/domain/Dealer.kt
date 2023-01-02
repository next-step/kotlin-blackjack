package blackjack.domain

class Dealer(val states: MutableList<State> = mutableListOf()) : Person(name = "딜러") {

    override fun checkDrawable(): Boolean = (ownCards.sumCardNumber() <= LEAST_CARD_SUM)

    fun checkResult(gamer: List<Gamer>) {
        gamer.forEach {
            states.add(convertGamerStateToCheckDealerState(it.state))
        }
    }

    private fun convertGamerStateToCheckDealerState(state: State): State =
        if (state == State.LOSE) State.WIN else State.LOSE

    companion object {
        const val LEAST_CARD_SUM = 16
    }
}
