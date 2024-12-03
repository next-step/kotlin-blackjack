package blackjack.domain

class Dealer(
    private val name: PlayerName,
    private val hand: Hand,
) : Participant(name, hand) {
    override fun isDrawable(): Boolean {
        return hand.calculateBestTotal() <= 16
    }

    companion object {
        fun createNew(cards: List<Card>): Dealer {
            return Dealer(PlayerName("딜러"), Hand.createInitial(cards))
        }
    }
}
