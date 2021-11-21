package blackjack.error

class InvalidMapToStayException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s' 타입은 Stay 타입으로 전환이 불가능합니다"
    }
}
