package blackjack.domain

import blackjack.domain.CardScoreCalculator.isOverScore

enum class GameResult(
    val message: String
) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private fun opposite(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }

    companion object {
        fun resultOfDealer(players: Players, dealer: Dealer): List<GameResult> {
            return players.map{
                GameResult.resultOfPlayer(it, dealer).opposite()
            }
        }

        fun resultOfPlayer(player: Player, dealer: Dealer): GameResult {
            val isPlayerScoreOver = isOverScore(player.cards, dealer.cards)
            return when {
                isOverScore(dealer.cards, CardScoreCalculator.BLACKJACK) || isPlayerScoreOver -> WIN
                !isPlayerScoreOver -> LOSE
                else -> DRAW
            }
        }
    }
}
