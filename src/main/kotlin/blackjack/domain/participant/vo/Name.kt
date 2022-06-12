package blackjack.domain.participant.vo

private const val DEALER_NAME: String = "딜러"

@JvmInline
value class Name(
    val value: String
) {
    init {
        require(value.isNotEmpty()) { "이름은 비어있을수 없습니다." }
    }

    companion object {
        fun dealer(): Name {
            return Name(DEALER_NAME)
        }
    }
}
