package blackjack.domain

import blackjack.util.CardSelector
import blackjack.util.RandomCardSelector

class BlackjackGame(
    userNames: UserNames,
    private val cardSelector: CardSelector = RandomCardSelector(),
    userDrawInterface: UserDrawInterface = UserDrawInterface.defaultDrawInterface,
) {
    val dealer = Dealer(getInitDeck())
    val users: Users

    init {
        val userList = userNames.map { name -> User(name, getInitDeck(), userDrawInterface) }.toSet()
        users = Users(userList)
    }

    private fun getInitDeck(): Deck {
        val cardList = mutableListOf<Card>()
        repeat(INITIAL_DECK_SIZE) {
            cardList.add(cardSelector.drawCard())
        }
        return Deck(cardList)
    }

    fun dealUsers(afterHit: (User) -> Unit) {
        for (user in users) {
            playerHit(user, afterHit)
        }
    }

    fun dealDealer(afterHit: (User) -> Unit = {}) {
        playerHit(dealer, afterHit)
    }

    private fun playerHit(user: User, afterHit: (User) -> Unit) {
        while (user.canDraw()) {
            addCardTo(user)
            afterHit(user)
        }
    }

    private fun addCardTo(user: User) {
        user.addCard(cardSelector.drawCard())
    }

    fun getGameResult(): BlackjackResults {
        return BlackjackResults(dealer, users)
    }

    companion object {
        private const val INITIAL_DECK_SIZE = 2
    }
}
