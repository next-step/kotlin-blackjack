package blackjack.domain

class Player(
    name: String,
    cards: Cards = Cards()
) : BlackJackPlayer(name, cards) {

    override fun isHit(): Boolean {
        return !cards.score().burst()
    }
}
