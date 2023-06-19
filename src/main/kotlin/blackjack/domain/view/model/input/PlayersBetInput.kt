package blackjack.domain.view.model.input

@JvmInline
value class PlayersBetInput(private val playersBet: List<PlayerBetInput>) : List<PlayerBetInput> by playersBet
