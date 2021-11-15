package blackjack.error

class InvalidPlayerNameException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s'는 유효한 이름이 아닙니다."
    }
}
