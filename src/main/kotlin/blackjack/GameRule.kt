package blackjack

import blackjack.domains.deck.Cards
import blackjack.domains.deck.Deck
import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Gamers
import blackjack.domains.participants.Player
import blackjack.domains.participants.User
import blackjack.views.Output.printDealerDraw
import blackjack.views.Output.printHaveCards

class GameRule(private val deck: Deck) {
    fun initGame(playerNames: List<String>): Gamers {
        val dealer = initUser(playerName = "딜러", isDealer = true)
        val players = playerNames.map { playerName ->
            initUser(playerName = playerName, isDealer = false)
        }
        return Gamers(players.plus(dealer))
    }

    fun drawCard(user: User) {
        while (true) {
            if (!user.isDrawable()) break
            user.drawCard(deck.drawCard())
            when (user) {
                is Dealer -> printDealerDraw()
                is Player -> printHaveCards(user.name, user.cards)
                else -> throw IllegalArgumentException("Unknown User Type")
            }
        }
    }

    private fun initUser(playerName: String, isDealer: Boolean): User {
        val cards = Cards((1..FIRST_DRAW_COUNT).map { deck.drawCard() }.toMutableList())
        val user = if (isDealer) Dealer(name = playerName) else Player(playerName)
        user.startGame(cards)
        printHaveCards(user.name, user.cards)
        return user
    }

    companion object {
        const val BLACKJACK = 21
        const val DEALER_DRAW_CONDITION = 17
        const val FIRST_DRAW_COUNT = 2
    }
}
