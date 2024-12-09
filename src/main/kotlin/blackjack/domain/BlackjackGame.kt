package blackjack.domain

class BlackjackGame(
    val participants: Participants,
    val deck: Deck,
) {
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
            bettingMoneys: List<BettingMoney>,
            deck: Deck,
        ): BlackjackGame {
            deck.shuffle()
            val dealer = Dealer.createNew(listOf(deck.drawCard(), deck.drawCard()))
            val players =
                playerNames.map { name ->
                    Player.createNew(
                        name,
                        bettingMoneys[playerNames.indexOf(name)],
                        listOf(deck.drawCard(), deck.drawCard()),
                    )
                }
            return BlackjackGame(Participants(dealer, players), deck)
        }
    }
}
