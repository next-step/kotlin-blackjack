package blackjack.model

class Participants(
    val players: Players,
    val dealer: Dealer
) {

    fun initialCardDealing() {
        players.initialCardDealing(dealer)
        dealer.initialCardDealing()
    }

    fun makeResult() {
        dealer.whoseWinner(players)
    }
}
