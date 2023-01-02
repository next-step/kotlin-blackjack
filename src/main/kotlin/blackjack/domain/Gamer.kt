package blackjack.domain

class Gamer(override val name: String) : Person() {

    fun isWin(dealer: Dealer): Boolean {
        return (ownCards.sumCardNumber() < Draw.DRAW_LIMIT && ownCards.sumCardNumber() > dealer.ownCards.sumCardNumber())
                || dealer.state == State.LOSE
    }
}
