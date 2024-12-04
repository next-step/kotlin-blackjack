package blackjack.domain

class Dealer(
    private val name: PlayerName,
    private val hand: Hand,
) : Participant(name, hand) {
    override fun isDrawable(): Boolean {
        return !state.isFinished() && hand.calculateBestTotal() in 1..16
    }

    fun getFirstCard(): String {
        return hand.getFirstCard()
    }

    fun getDealerName(): String {
        return name.value
    }

    companion object {
        fun createNew(cards: List<Card>): Dealer {
            return Dealer(PlayerName("딜러"), Hand.createInitial(cards))
        }
    }
}
