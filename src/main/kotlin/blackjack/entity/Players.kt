package blackjack.entity

class Players(val participants: List<Player>) {
    fun initializeHands(deck: Deck) {
        participants.forEach { player ->
            repeat(2) { player.receiveCard(deck.deal()) }
        }
    }

    fun describeHands(): List<PlayerHand> {
        return participants.map { PlayerHand(it.name, it.hand) }
    }
}
