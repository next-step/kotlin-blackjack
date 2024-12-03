package blackjack.domain

class Player(
    private val name: PlayerName,
    private val hand: Hand,
) : Participant(name, hand) {
    override fun isDrawable(): Boolean {
        return hand.calculateBestTotal() != 0
    }

    companion object {
        fun createNew(
            playerName: PlayerName,
            cards: List<Card>,
        ): Player {
            return Player(playerName, Hand.createInitial(cards))
        }
    }
}
