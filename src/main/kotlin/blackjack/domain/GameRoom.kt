package blackjack.domain

class GameRoom(
    val dealer: Dealer = Dealer(),
    val players: List<Player>,
    private val deck: Deck = Deck(),
) {
    val participants = listOf(dealer) + players

    init {
        dealInitialCards()
    }

    private fun dealInitialCards() {
        participants.forEach { participant ->
            participant.receiveCard(deck.drawCard())
            participant.receiveCard(deck.drawCard())
        }
    }

    fun drawCard(participant: Participant) {
        participant.receiveCard(deck.drawCard())
    }

    fun calculateResult(): GameResult {
        val playerResults = players.map { player ->
            PlayerResult(
                name = player.name,
                cards = player.cards,
                score = player.cards.calculateScore(),
                isWinner = player.isWinner(dealer),
            )
        }

        val dealerResult = DealerResult(
            name = dealer.name,
            cards = dealer.cards,
            score = dealer.cards.calculateScore(),
            winCount = playerResults.count { !it.isWinner },
            loseCount = playerResults.count { it.isWinner },
        )

        return GameResult(dealerResult, playerResults)
    }
}
