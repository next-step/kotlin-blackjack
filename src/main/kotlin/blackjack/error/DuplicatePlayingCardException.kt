package blackjack.error

class DuplicatePlayingCardException : RuntimeException() {
    override val message = "이미 덱에 존재하는 카드가 있습니다."
}
