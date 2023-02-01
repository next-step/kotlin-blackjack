package blackjack

import blackjack.domains.deck.Deck
import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Player
import blackjack.domains.participants.User
import blackjack.views.Output

class CardDraw(private val deck: Deck) {

    fun draw(user: User) {
        while (true) {
            if (!user.isDrawable()) break
            user.drawCard(deck.drawCard())
            when (user) {
                is Dealer -> Output.printDealerDraw()
                is Player -> Output.printHaveCards(user.name, user.cards)
                else -> throw IllegalArgumentException("Unknown User Type")
            }
        }
    }
}
