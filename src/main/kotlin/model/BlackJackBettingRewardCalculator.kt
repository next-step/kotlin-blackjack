package model

class BlackJackBettingRewardCalculator(
    private val player: Player,
    private val dealer: Player
) {

    fun reward(): Int {
        val playerScore = BlackJackScore(player.cards).score
        val dealerScore = BlackJackScore(dealer.cards).score

        if (player.cards.count() == 2 && playerScore == BLACK_JACK_MAX_SCORE) {
            return (player.bet * 1.5).toInt()
        }

        if ((dealerScore == BLACK_JACK_MAX_SCORE && playerScore == BLACK_JACK_MAX_SCORE) ||
            dealerScore > BLACK_JACK_MAX_SCORE ||
            (playerScore in (dealerScore + 1)..BLACK_JACK_MAX_SCORE)
        ) {
            return player.bet
        }

        if (dealerScore == playerScore) {
            return 0
        }

        return player.bet * -1
    }
}
