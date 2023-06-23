package blackjack.model

data class BlackjackPlayer(val name: PlayerName, val deck: HandDeck = HandDeck()) {

    fun addedCard(card: TrumpCard): BlackjackPlayer {
        return BlackjackPlayer(name, deck + card)
    }
}
