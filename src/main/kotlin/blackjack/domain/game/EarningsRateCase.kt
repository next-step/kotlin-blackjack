package blackjack.domain.game

/**
 * ### 도전자의 승패 결과에 따른 수익률을 정의한 enum 입니다.
 */
enum class EarningsRateCase(
    val earningsRate: Double,
    private val gameResult: GameResultType,
) {
    CHALLENGER_WIN_BLACKJACK(1.5, GameResultType.CHALLENGER_WIN),
    CHALLENGER_WIN(1.0, GameResultType.CHALLENGER_WIN),
    DEALER_WIN(-1.0, GameResultType.DEALER_WIN),
    DRAW(0.0, GameResultType.DRAW),
    ;

    /**
     * ### 도전자가 승리한 상태에서 도전자 혼자 블랙잭이라면 블랙잭 수익률을 반환하며 그외에는 도전자의 승리 여부에 따라 수익률을 반환합니다
     */
    companion object {
        fun of(
            gameResultType: GameResultType,
            challengerBlackjacked: Boolean = false,
            dealerBlackjacked: Boolean = false
        ): EarningsRateCase {
            if (gameResultType == GameResultType.CHALLENGER_WIN && challengerBlackjacked && dealerBlackjacked.not()) {
                return CHALLENGER_WIN_BLACKJACK
            }
            return values().filter { it != CHALLENGER_WIN_BLACKJACK }
                .find { it.gameResult == gameResultType }
                ?: DRAW
        }
    }
}
