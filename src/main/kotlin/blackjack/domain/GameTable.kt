package blackjack.domain

class GameTable(
    private val users: List<User>,
    private val deck: Deck,
) {
    fun dealInitCard(): List<User> {
        return users.map { user ->
            (1..INIT_CARD_DRAW_COUNT).fold(user) { acc, _ ->
                acc.receiveCard(deck.draw())
            }
        }
    }

    companion object {
        const val INIT_CARD_DRAW_COUNT = 2
    }
}
