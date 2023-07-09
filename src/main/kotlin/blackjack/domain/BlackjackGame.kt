package blackjack.domain

import blackjack.domain.card.CardReceiveSelector
import blackjack.domain.card.Cards
import blackjack.domain.card.InputCardReceiveSelector
import blackjack.domain.card.PrintUserCards
import blackjack.domain.card.UserCards
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

    fun handsOutCards(cardReceiveSelector: InputCardReceiveSelector, printUserCards: PrintUserCards) {
        cardReceivePossibleUsers().forEach {
            cardReceiveWant(it, cardReceiveSelector, printUserCards)
        }

        dealerCardReceive()
    }

    private fun cardReceiveWant(
        user: Player,
        cardReceiveSelector: CardReceiveSelector,
        uerCards: UserCards
    ) {

        if (cardReceiveSelector.cardReceiveNotWant(user.name)) {
            return
        }

        user.userCardReceive(gameDeck.handOutCard())

        uerCards.printUserCards(user.name, user.cards)

        if (user.isDeckInComplete()) {
            cardReceiveWant(user, cardReceiveSelector, uerCards)
        }
    }

    private fun dealerCardReceive() {
        if (dealer.cardReceivePossible()) {
            dealer.plusCard(gameDeck.handOutCard())
        }
    }

    companion object {
        const val GAME_START_CARD_COUNT = 2
        const val BLACKJACK_VALUE = 21

        fun from(userNames: Map<String, Int>): BlackjackGame {
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
