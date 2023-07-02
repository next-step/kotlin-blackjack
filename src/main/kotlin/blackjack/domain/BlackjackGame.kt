package blackjack.domain

class BlackjackGame(userNames: String) {
    private val users: Users
    private val gameDeck: GameDeck = GameDeck()

    fun userCards(): Map<User, Cards> {
        return users.userCards()
    }

    fun cardReceivePossibleUsers(): List<User> {
        return users.users.filter { user -> !user.isDeckComplete }
    }

    fun handOutCard(): Card {
        return gameDeck.handOutCard()
    }

    init {
        users = Users(
            userNames
                .split(USER_NAME_SPLIT_SYMBOL)
                .map {
                    User(it, Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT)))
                }
        )
    }

    companion object {
        private const val USER_NAME_SPLIT_SYMBOL = ","
        const val GAME_START_CARD_COUNT = 2
        const val BLACKJACK_VALUE = 21
    }
}
