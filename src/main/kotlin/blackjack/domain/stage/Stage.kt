package blackjack.domain.stage

interface Stage {
    fun progress()

    fun emitResult()

    fun nextStage(): Stage
}
