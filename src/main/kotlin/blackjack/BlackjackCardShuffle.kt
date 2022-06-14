package blackjack

class BlackjackCardShuffle : CardShuffle {
    override fun getCards(): List<Card> {
        return Cards.getCards()
            .shuffled()
            .toList()
    }
}
