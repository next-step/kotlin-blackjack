package blackjack.domain

class Player(val name: String) {
    private val hands = mutableListOf<PokerCard>()

    fun receiveCard(vararg pokerCards: PokerCard) {
        for (pokerCard in pokerCards) {
            hands.add(pokerCard)
        }
    }

    fun hands(): List<PokerCard> {
        return hands.toList()
    }
    fun showHands(): String {
        return hands.joinToString(transform = PokerCard::representCard)
    }
}
