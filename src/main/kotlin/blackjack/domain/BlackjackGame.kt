package blackjack.domain

class BlackjackGame(
    val participants: Participants,
    private val deck: Deck,
) {
    fun drawCard(participant: Participant) {
        val newCard = deck.drawCard()
        participant.addCard(newCard)
    }

    fun makeGameResult(): BlackjackGameResult {
        return BlackjackGameResult(participants.dealer, participants.players)
    }

    fun getPlayers(): List<Player> {
        return participants.players
    }

    fun getDealer(): Dealer {
        return participants.dealer
    }

    companion object {
        fun createGame(
            playerNames: List<PlayerName>,
            deck: Deck,
        ): BlackjackGame {
            deck.shuffle()
            val dealer = Dealer.createNew(listOf(deck.drawCard(), deck.drawCard()))
            val players =
                playerNames.map { name ->
                    val handCards = listOf(deck.drawCard(), deck.drawCard())
                    Player.createNew(name, handCards)
                }
            return BlackjackGame(Participants(dealer, players), deck)
        }
    }
}
