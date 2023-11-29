package blackjack.domain.result.game

@JvmInline
value class VictoryStatues(
    val value: List<VictoryStatus>,
) {
    val winCount: Int
        get() = value.count { it == VictoryStatus.WIN }

    val pushCount: Int
        get() = value.count { it == VictoryStatus.PUSH }

    val lossCount: Int
        get() = value.count { it == VictoryStatus.LOSS }
}
