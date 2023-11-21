package blackJack.model

class Player(
    val name: String,
    var hand: List<Card> = listOf()
) {
    fun addCard(card: Card) {
        hand += card
    }

    fun calculateScore(): Int {
        return hand.sumOf { it.rank.score }
    }

    fun askMoreCard(dealer: Dealer) {
        if (dealer.isDrawCardAllowedFor(this).not()) {
            return
        }

        addCard(dealer.drawCard())
    }
}
