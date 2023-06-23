package blackjack.model

data class BlackjackPlayer(
    val name: PlayerName,
    private val scoreJudge: BlackjackScoreJudge,
    val deck: HandDeck = HandDeck(),
) {

    fun addedCard(card: TrumpCard): BlackjackPlayer {
        return BlackjackPlayer(name, scoreJudge, deck + card)
    }

    val deckScore: Int
        get() {
            return scoreJudge.score(deck)
        }
}
