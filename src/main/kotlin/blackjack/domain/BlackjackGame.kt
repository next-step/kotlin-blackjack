package blackjack.domain

import blackjack.domain.result.DealerResult
import blackjack.domain.users.Dealer
import blackjack.domain.users.Player
import blackjack.domain.users.User
import blackjack.domain.users.Users
import blackjack.model.UserCards

class BlackjackGame(
    dealer: Dealer,
    userList: List<Player>,
    private val gameDeck: GameDeck = GameDeck()
) {
    val users: Users = Users(userList, dealer)

    init {
        userList.forEach { require(it.cardSize() == GAME_START_CARD_COUNT) }
    }

    fun userCards(): Map<User, Cards> {
        return users.userCards
    }

    fun cardReceivePossibleUsers(): List<Player> {
        return users.cardReceivePossibleUsers()
    }

    fun handOutCard(): Card {
        return gameDeck.handOutCard()
    }

    fun playerCards(): Map<Player, Cards> {
        return users.playerCards()
    }

    fun dealerCardValue(): Int {
        return users.dealerCardValue()
    }

    fun updateDealerResult(dealerResult: DealerResult) {
        users.dealer.dealerResult = dealerResult
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
                    Player(UserCards(it, Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT))))
                }
            val dealer = Dealer(UserCards("딜러", Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT))))

            return BlackjackGame(dealer, users, gameDeck)
        }
    }
}
