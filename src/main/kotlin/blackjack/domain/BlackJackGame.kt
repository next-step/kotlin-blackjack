package blackjack.domain

const val BLACK_JACK = 21
const val DEALER_MINIMUM_SCORE = 16

class BlackJackGame(
    val dealer: Dealer = Dealer(),
    players: Players,
) {
    var players: Players = players
        private set

    fun handOutDefaultCardToPlayers() {
        players = players.receiveDefaultCards(dealer)
        dealer.receiveCards(dealer.dealDefaultCard())
    }

    fun handOutAdditionalCardTo(participant: Participant) {
        participant.receiveCard(dealer.dealCard())
    }
}
