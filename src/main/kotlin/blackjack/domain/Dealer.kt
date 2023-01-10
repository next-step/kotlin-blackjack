package blackjack.domain

class Dealer(
    name: String,
    cards: Cards = Cards(),
) : Participant(
    name = name,
    cards = cards,
) {
    override fun canHit(): Boolean {
        return cards.getScore() < 17
    }
}
