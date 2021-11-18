package blackJack.domain

class DealerResult(
    val win: Int = 0,
    val lose: Int = 0,
    val draw: Int = 0
) {

    companion object {
        fun winOrLose(playerResults: PlayerResults): DealerResult =
            playerResults.toList().groupingBy {
                it.winDrawLose
            }.eachCount().run {
                DealerResult(
                    this[WinDrawLose.LOSE] ?: 0,
                    this[WinDrawLose.WIN] ?: 0,
                    this[WinDrawLose.DRAW] ?: 0,
                )
            }
    }
}
