package blackjack.domain

object GameTable {
    const val INIT_CARD_DRAW_COUNT = 2

    fun dealInitCard(
        users: List<User>,
        deck: Deck,
    ): List<User> {
        return users.map { user ->
            (1..INIT_CARD_DRAW_COUNT).fold(user) { acc, _ ->
                acc.receiveCard(deck.draw())
            }
        }
    }
}
