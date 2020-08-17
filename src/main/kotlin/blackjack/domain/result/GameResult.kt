package blackjack.domain.result

import blackjack.domain.player.Player

enum class GameResult(
    private val gameResultStrategy: GameResultStrategy,
    val earnMoney: (player: Player) -> Int
) {
    WIN(WinResultStrategy(), { if (it.isBlackJack()) (it.getBettingMoney() * 1.5).toInt() else it.getBettingMoney() }),
    LOSE(LoseResultStrategy(), { -it.getBettingMoney() }),
    DRAW(DrawResultStrategy(), { 0 });

    companion object {
        fun ofChallenger(dealer: Player, challenger: Player): GameResult {
            return values().find { it.gameResultStrategy.isMatch(dealer, challenger) }
                ?: throw IllegalStateException("딜러(${dealer.getScore()}점)와 도전자(${challenger.getScore()}점)의 게임 결과를 찾을 수 없습니다.")
        }
    }
}
