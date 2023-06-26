package blackjack.domain

class BlackjackGame(
    val players: List<Player>,
    val dealer: Dealer
) {

    companion object {
        const val BLACK_JACK_NUMBER = 21
    }
}
