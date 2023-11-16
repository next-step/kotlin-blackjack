package blackjack.domain.stage

interface Stage {
    fun receiveSetupData()

    fun progress()

    fun handleResult()

    fun nextStage(): Stage
}
