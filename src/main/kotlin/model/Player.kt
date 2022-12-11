package model

private const val BLACK_JACK_MAX_SCORE = 21

open class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    var bet: Int = 0

    fun hit(card: Card) = cards.add(card)

    fun competeWith(dealer: Dealer): BlackJackGameResult {
        val dealerScore = PokerScore(dealer.cards).score
        val playerScore = PokerScore(cards).score

        if (dealerScore > BLACK_JACK_MAX_SCORE ||
            (playerScore in (dealerScore + 1)..BLACK_JACK_MAX_SCORE)
        ) {
            return BlackJackGameResult.WIN
        }

        if (dealerScore == playerScore) {
            return BlackJackGameResult.DRAW
        }

        return BlackJackGameResult.LOSE
    }
}
