package blackjack.domain

class Dealer(
    private val name: PlayerName,
) : Participant(name) {
    override fun isDrawable(): Boolean {
        return !state.isFinished() && state.hand().calculateBestTotal() in 1..16
    }

    override fun getInitialCard(): List<Card> {
        return state.hand().getSpecificRangeCards(0, 1)
    }

    companion object {
        fun createNew(cards: List<Card>): Dealer {
            val dealer = Dealer(PlayerName("딜러"))
            cards.forEach { dealer.addCard(it) }
            return dealer
        }
    }
}
