package model

private const val BLACK_JACK_INIT_CARD_COUNT = 2

private const val BLACK_JACK_REWARD_RATE = 1.5

class BlackJackBettingRewardCalculator(
    private val player: Player,
    private val dealer: Player
) {

    fun reward(): Int {
        val playerScore = BlackJackScore(player.cards).score
        val dealerScore = BlackJackScore(dealer.cards).score

        if (isBlackJack(playerScore)) {
            return (player.bet * BLACK_JACK_REWARD_RATE).toInt()
        }

        if ((dealerScore == BLACK_JACK_SCORE && playerScore == BLACK_JACK_SCORE) ||
            isBust(dealerScore) ||
            (playerScore in (dealerScore + 1)..BLACK_JACK_SCORE)
        ) {
            return player.bet
        }

        if (dealerScore == playerScore) {
            return 0
        }

        return player.bet * -1
    }

    private fun isBust(dealerScore: Int) = dealerScore > BLACK_JACK_SCORE

    private fun isBlackJack(playerScore: Int): Boolean {
        return player.cards.count() == BLACK_JACK_INIT_CARD_COUNT && playerScore == BLACK_JACK_SCORE
    }
}
