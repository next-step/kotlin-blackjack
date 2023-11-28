package blackjack

class GameParticipantDealer(
    name: String = NAME,
    cards: List<Card> = emptyList(),
    betAmount: Int = 0
) : GameParticipant(name, cards, betAmount) {

    fun compareScore(player: GameParticipantPlayer): MatchResult {
        val playerScore = player.getScore()
        val dealerScore = this.getScore()
        return if (player.isBust || this.isBlackjack()) MatchResult.LOSS
        else if (player.isBlackjack()) MatchResult.BLACKJACK
        else if (this.isBust) MatchResult.WIN
        else if (dealerScore < playerScore) MatchResult.WIN
        else if (playerScore < dealerScore) MatchResult.LOSS
        else MatchResult.DRAW
    }

    override fun isNotAllowedDealing(): Boolean = getScore() > CONTINUE_DEALING_SCORE

    override fun receiveCard(card: Card): GameParticipantDealer = GameParticipantDealer(
        name = this.name,
        cards = this.cards + card,
        betAmount = this.betAmount
    )

    companion object {
        const val NAME = "딜러"
        private const val CONTINUE_DEALING_SCORE = 16
    }
}
