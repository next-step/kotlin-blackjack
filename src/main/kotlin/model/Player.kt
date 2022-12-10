package model

private const val BLACK_JACK_MAX_SCORE = 21

open class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    fun hit(card: Card) = cards.add(card)

    fun competeWith(dealer: Dealer): WinOrLose {
        val dealerScore = PokerScore(dealer.cards).score
        val playerScore = PokerScore(cards).score

        if (dealerScore > BLACK_JACK_MAX_SCORE ||
            playerScore in dealerScore..BLACK_JACK_MAX_SCORE
        ) {
            return WinOrLose.WIN
        }

        return WinOrLose.LOSE
    }
}
