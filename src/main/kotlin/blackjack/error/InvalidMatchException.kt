package blackjack.error

class InvalidMatchException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s' 타입은 매칭을 할 수 없다"
    }
}
