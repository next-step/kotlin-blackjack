package blackjack.domain

class Player(name: String) : Participant(name) {
    override fun canDrawMoreCard(): Boolean {
        return cards.canDrawMoreCard()
    }
}
