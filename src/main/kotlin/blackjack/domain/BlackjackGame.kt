package blackjack.domain

import blackjack.domain.result.GameResults
import blackjack.domain.users.Dealer
import blackjack.domain.users.Player
import blackjack.domain.users.User
import blackjack.domain.users.Users

class BlackjackGame(
    dealer: Dealer,
    userList: List<Player>,
    private val gameDeck: GameDeck = GameDeck()
) {
    private val users: Users = Users(userList, dealer)
    private val dealer: Dealer = users.dealer

    init {
        userList.forEach { require(it.cardSize() == GAME_START_CARD_COUNT) }
    }

    fun userCards(): Map<User, Cards> {
        return users.userCards
    }

    fun cardReceivePossibleUsers(): List<Player> {
        return users.cardReceivePossibleUsers()
    }

    fun calculateBlackjackResult(): GameResults {
        return users.calculateGameResult()
    }

    fun userCardReceive(user: Player) {
        user.userCardReceive(handOutCard())
    }

    fun dealerCardReceive() {
        if (dealer.cardReceivePossible()) {
            dealer.plusCard(gameDeck.handOutCard())
        }
    }

    private fun handOutCard(): Card {
        return gameDeck.handOutCard()
    }

    companion object {
        const val GAME_START_CARD_COUNT = 2
        const val BLACKJACK_VALUE = 21

        fun from(userNames: MutableMap<String, Int>): BlackjackGame {
            val gameDeck = GameDeck()
            val users = userNames
                .map {
                    Player(it.key, Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT)), it.value)
                }
            val dealer = Dealer("딜러", Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT)))

            return BlackjackGame(dealer, users, gameDeck)
        }
    }
}
