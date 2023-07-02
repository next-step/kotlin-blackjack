package blackjack.domain

class BlackjackGame(userNames: String) {
    val users: List<User>
    val gameDeck: GameDeck = GameDeck()

    init {
        users = userNames
            .split(USER_NAME_SPLIT_SYMBOL)
            .map { User(it, Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT))) }

    }

    companion object {
        private const val USER_NAME_SPLIT_SYMBOL = ","
        const val GAME_START_CARD_COUNT = 2
    }
}
