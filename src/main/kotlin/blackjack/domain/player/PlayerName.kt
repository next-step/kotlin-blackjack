package blackjack.domain.player

@JvmInline
value class PlayerName(
    val value: String,
) {
    init {
        require(value.isNotBlank()) {
            "플레이어 이름은 공백이 될 수 없습니다"
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        fun dealerName(): PlayerName = PlayerName(DEALER_NAME)
    }
}
