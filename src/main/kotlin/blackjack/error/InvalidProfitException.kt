package blackjack.error

class InvalidProfitException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s' 타입은 이윤을 구할 수 없다"
    }
}
