package blackjack.domain.result

import blackjack.domain.player.Player

enum class GameResult(
    private val gameResultStrategy: GameResultStrategy,
    private val earnRatio: (player: Player) -> Double
) {
    WIN(WinResultStrategy(), { if (it.isBlackJack()) 1.5 else 1.0 }),
    LOSE(LoseResultStrategy(), { -1.0 }),
    DRAW(DrawResultStrategy(), { 0.0 });

    companion object {
        fun ofChallenger(dealer: Player, challenger: Player): GameResult {
            return values().find { it.gameResultStrategy.isMatch(dealer, challenger) }
                ?: throw IllegalStateException("딜러(${dealer.getScore()}점)와 도전자(${challenger.getScore()}점)의 게임 결과를 찾을 수 없습니다.")
        }

        fun ofDealer(dealer: Player, challenger: Player): GameResult {
            return when (ofChallenger(dealer, challenger)) {
                WIN -> LOSE
                LOSE -> WIN
                DRAW -> DRAW
            }
        }
    }
}
