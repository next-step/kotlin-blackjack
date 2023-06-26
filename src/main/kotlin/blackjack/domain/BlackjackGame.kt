package blackjack.domain

import blackjack.util.CardSelector
import blackjack.util.RandomCardSelector

class BlackjackGame(userNames: UserNames, private val cardSelector: CardSelector = RandomCardSelector()) {
    val dealer = Dealer(getInitDeck())
    val users: Users

    init {
        val userList = userNames.map { name -> User(name, getInitDeck()) }.toSet()
        users = Users(userList)
    }

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

    private fun playerHit(user: User, checkHit: (User) -> Boolean, afterHit: (User) -> Unit) {
        while (checkHit(user)) {
            addCardTo(user)
            afterHit(user)
        }
    }

    fun dealUsers(checkHit: (User) -> Boolean, afterHit: (User) -> Unit) {
        for (user in users) {
            playerHit(user, checkHit, afterHit)
        }
    }

    fun dealDealer(afterHit: (User) -> Unit = {}) {
        playerHit(dealer, ::checkDealerHit, afterHit)
    }

    private fun checkDealerHit(dealer: User): Boolean {
        return dealer.calculatePoint() <= DEALER_HIT_THRESHOLD
    }

    fun getGameResult(): BlackjackResults {
        return BlackjackResults(dealer, users)
    }

    companion object {
        private const val INITIAL_DECK_SIZE = 2
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
