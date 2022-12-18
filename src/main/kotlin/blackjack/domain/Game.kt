package blackjack.domain

class Game(
    val players: Players,
    val deck: Deck = Deck()
) {
    fun getDealer() = Dealer().initialCard(deck)

    fun initialCard(): Players {
        return players.initialCard(deck)
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}
