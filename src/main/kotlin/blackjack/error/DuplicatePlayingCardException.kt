package blackjack.error

object DuplicatePlayingCardException : RuntimeException() {
    private const val MESSAGE = "이미 덱에 존재하는 카드가 있습니다."

    override val message: String
        get() = MESSAGE
}
