package blackjack

import blackjack.domains.deck.Deck
import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Player
import blackjack.domains.participants.User
import blackjack.views.Output

class CardDraw(private val deck: Deck) {
    private var canPrintDealerDraw = true
    fun draw(user: User) {
        while (true) {
            if (!user.isDrawable()) break
            user.drawCard(deck.drawCard())
            when (user) {
                is Dealer -> {
                    if(canPrintDealerDraw) {
                        Output.printDealerDraw()
                        canPrintDealerDraw = false
                    }
                }
                is Player -> Output.printHaveCards(user.name, user.cards)
            }
        }
    }
}
