package blackjack

class Player(name: String, initialCards: List<Card>) : Participant(name, initialCards) {
    fun isInitialState(): Boolean = hand.size == 2
}
