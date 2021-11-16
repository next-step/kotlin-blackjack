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
            }.eachCount().let {
                DealerResult(
                    it[WinDrawLose.LOSE] ?: 0,
                    it[WinDrawLose.WIN] ?: 0,
                    it[WinDrawLose.DRAW] ?: 0,
                )
            }
    }
}
