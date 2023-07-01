package blackjack.domain

import blackjack.util.CardSelector
import blackjack.util.RandomCardSelector

class BlackjackGame(
    userNames: UserNames,
    private val cardSelector: CardSelector = RandomCardSelector(),
    userDrawInterface: UserDrawInterface,
) {
    val dealer = Dealer(getInitCards())
    val users: Users

    init {
        val userList = userNames.map { name -> User(name, getInitCards(), userDrawInterface) }.toSet()
        users = Users(userList)
    }

    private fun getInitCards(): Cards {
        val cardList = List(INITIAL_CARDS_SIZE) {
            cardSelector.drawCard()
        }

        return Cards(cardList)
    }

    fun dealUsers(afterHit: (Player) -> Unit) {
        for (user in users) {
            playerHit(user, afterHit)
        }
    }

    fun dealDealer(afterHit: (Player) -> Unit = {}) {
        playerHit(dealer, afterHit)
    }

    private fun playerHit(player: Player, afterHit: (Player) -> Unit) {
        while (player.canDraw()) {
            addCardTo(player)
            afterHit(player)
        }
    }

    private fun addCardTo(player: Player) {
        player.addCard(cardSelector.drawCard())
    }

    fun getGameResult(): BlackjackResults {
        return BlackjackResults(dealer, users)
    }

    companion object {
        private const val INITIAL_CARDS_SIZE = 2
    }
}
