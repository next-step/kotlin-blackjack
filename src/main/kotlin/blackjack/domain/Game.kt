package blackjack.domain

class Game(
    private val deck: Deck,
    private val participants: GameParticipants,
) {
    fun start() {
        participants.distributeInitialCards(deck)
    }

    fun hit(playerName: String) {
        participants.players.hit(playerName, deck)
    }

    fun dealerPlayTurn() {
        participants.dealer.playTurn(deck)
    }

    fun calculateResults(): GameResult {
        return GameResultCalculator.calculate(participants.dealer, participants.players)
    }
}
