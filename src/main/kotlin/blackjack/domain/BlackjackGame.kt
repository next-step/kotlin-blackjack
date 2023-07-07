package blackjack.domain

import blackjack.domain.result.DealerResult
import blackjack.domain.result.GameResults
import blackjack.domain.result.PlayerResult
import blackjack.domain.users.Dealer
import blackjack.domain.users.Player
import blackjack.domain.users.User
import blackjack.domain.users.Users

class BlackjackGame(
    dealer: Dealer,
    userList: List<Player>,
    private val gameDeck: GameDeck = GameDeck()
) {
    val users: Users = Users(userList, dealer)
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
        val dealerCardValue = users.dealerCardValue()

        if (dealerCardValue > BLACKJACK_VALUE) {
            return dealerLose()
        }

        return users.calculateGameResult(dealerCardValue)
    }

    fun handOutCard(): Card {
        return gameDeck.handOutCard()
    }

    fun dealerCardReceive() {
        if (dealer.cardReceivePossible()) {
            dealer.plusCard(gameDeck.handOutCard())
        }
    }

    private fun dealerLose() = GameResults(
        users.players.map { PlayerResult(it.name, true) },
        DealerResult(loseCount = users.players.size)
    )

    companion object {
        const val GAME_START_CARD_COUNT = 2
        const val BLACKJACK_VALUE = 21

        fun from(userNames: List<String>): BlackjackGame {
            val gameDeck = GameDeck()
            val users = userNames
                .map {
                    Player(it, Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT)))
                }
            val dealer = Dealer("딜러", Cards(gameDeck.handOutCards(GAME_START_CARD_COUNT)))

            return BlackjackGame(dealer, users, gameDeck)
        }
    }
}
