package blackjack.domain.stage

interface Stage {
    fun progress()

    fun handleResult()

    fun nextStage(): Stage
}
