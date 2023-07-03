package blackjack.domain

class BlackjackGame(
    userList: List<User>,
    private val gameDeck: GameDeck = GameDeck()
) {
    private val users: Users

    init {
        userList.forEach { require(it.cardSize() == GAME_START_CARD_COUNT) }
        users = Users(userList)
    }

    fun userCards(): Map<User, Cards> {
        return users.userCards
    }

    fun cardReceivePossibleUsers(): List<User> {
        return users.users.filter { user -> !user.isDeckComplete }
    }

    fun handOutCard(): Card {
        return gameDeck.handOutCard()
    }

    companion object {
        private const val USER_NAME_SPLIT_SYMBOL = ","
        const val GAME_START_CARD_COUNT = 2
        const val BLACKJACK_VALUE = 21

        fun from(userNames: String): BlackjackGame {
            val gameDeck = GameDeck()
            val users = userNames
                .split(USER_NAME_SPLIT_SYMBOL)
                .map {
                    User(it, Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT)))
                }

            return BlackjackGame(users, gameDeck)
        }
    }
}
