package blackjack.domain.result

import blackjack.domain.gamer.Player
import blackjack.domain.state.result.Lose
import blackjack.domain.state.result.Push
import blackjack.domain.state.result.Win

class DealerResult private constructor(
    val win: Int = 0,
    val push: Int = 0,
    val lose: Int = 0,
) {
    companion object {
        private const val DEFAULT = 0

        fun from(playerResults: List<Player>): DealerResult {
            return playerResults.groupingBy {
                it.state
            }.eachCount()
                .run {
                    DealerResult(
                        this[Lose] ?: DEFAULT,
                        this[Push] ?: DEFAULT,
                        this[Win] ?: DEFAULT,
                    )
                }
        }
    }
}
