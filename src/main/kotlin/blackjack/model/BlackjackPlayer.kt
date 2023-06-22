package blackjack.model

data class BlackjackPlayer(val name: String, val deck: CardDeck = emptyList()) {

    init {
        require(name.isNotBlank()) { "name must not be empty or blank" }
    }

    fun addedCard(card: TrumpCard): BlackjackPlayer {
        return BlackjackPlayer(name, deck + card)
    }
}
