package blackjack.error

class ReadLineNullPointerException : RuntimeException() {
    override val message: String = "입력된 값이 null 입니다"
}
