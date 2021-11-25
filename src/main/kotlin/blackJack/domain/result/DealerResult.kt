package blackJack.domain.result

import kotlin.math.abs

@JvmInline
value class DealerResult(
    val profit: Int
) {

    companion object {
        fun winOrLose(playerResults: PlayerResults): DealerResult =
            playerResults.toList().fold(0) { acc, playerResult ->
                when (playerResult.winDrawLose) {
                    WinDrawLose.WIN -> acc - playerResult.getProfit()
                    WinDrawLose.DRAW -> acc
                    WinDrawLose.LOSE -> acc + abs(playerResult.getProfit())
                }
            }.run {
                DealerResult(this)
            }
    }
}
