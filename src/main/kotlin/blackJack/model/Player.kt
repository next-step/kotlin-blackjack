package blackJack.model

class Player(
    val name: String,
    var hand: List<Card> = listOf()
) {
    fun calculateScore(): Int {
        return hand.sumOf { it.rank.score }
    }
}

infix fun Player.askMoreCard(dealer: Dealer) {
    if (dealer checkDrawCardIsAllowedFor this) {
        this requestCardToDealer dealer.drawCard()
    }
}

infix fun Player.requestCardToDealer(card: Card) {
    hand += card
}
