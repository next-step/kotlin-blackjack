package blackjack.domain

import blackjack.util.CardSelector
import blackjack.util.RandomCardSelector

class BlackjackGame(userNames: UserNames, private val cardSelector: CardSelector = RandomCardSelector()) {
    private val dealer = User(DEALER_NAME, getInitDeck())
    val users: Users

    init {
        val userList = userNames.map { name -> User(name, getInitDeck()) }.toSet()
        users = Users(userList)
    }

    fun getDealerDeck(): Deck = dealer.deck

    private fun getInitDeck(): Deck {
        val cardList = mutableListOf<Card>()
        repeat(INITIAL_DECK_SIZE) {
            cardList.add(cardSelector.drawCard())
        }
        return Deck(cardList)
    }

    private fun addCardTo(user: User) {
        user.addCard(cardSelector.drawCard())
    }

    private fun userHit(user: User, checkHit: (User) -> Boolean, afterHit: (User) -> Unit) {
        while (checkHit(user)) {
            addCardTo(user)
            afterHit(user)
        }
    }

    fun dealDealer(afterHit: (User) -> Unit = {}) {
        userHit(dealer, ::checkDealerHit, afterHit)
    }

    private fun checkDealerHit(dealer: User): Boolean {
        return dealer.calculatePoint() <= DEALER_HIT_THRESHOLD
    }

    fun dealUsers(checkHit: (User) -> Boolean, afterHit: (User) -> Unit) {
        for (user in users) {
            userHit(user, checkHit, afterHit)
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val INITIAL_DECK_SIZE = 2
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
