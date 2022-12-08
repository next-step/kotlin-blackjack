package model

private const val POKER_MAX_SCORE = 21

open class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    fun hit(card: Card) = cards.add(card)

    fun winOrLose(dealer: Dealer): WinOrLose {
        val dealerScore = PokerScore(dealer.cards).score
        val playerScore = PokerScore(cards).score

        if (dealerScore > POKER_MAX_SCORE ||
            playerScore in dealerScore..POKER_MAX_SCORE
        ) {
            return WinOrLose.WIN
        }

        return WinOrLose.LOSE
    }
}
