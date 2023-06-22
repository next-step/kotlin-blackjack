package blackjack.domain

class Player(val name: String) {
    private val hand = mutableListOf<PokerCard>()
    var wantToHit = true
    fun receiveCard(vararg pokerCards: PokerCard) {
        for (pokerCard in pokerCards) {
            hand.add(pokerCard)
        }
//        if (hand.total() > 21) wantToHit = false
    }

    fun hands(): List<PokerCard> {
        return hand.toList()
    }
    fun performAction(userChoice: Boolean, dealer: Dealer) {
        if (userChoice) {
            receiveCard(dealer.draw())
        } else {
            stay()
        }
    }

    fun stay() {
        this.wantToHit = false
    }

    fun showHands(): String {
        return hand.joinToString(transform = PokerCard::representCard)
    }
}
