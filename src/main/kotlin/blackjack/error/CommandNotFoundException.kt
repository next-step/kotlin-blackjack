package blackjack.error

class CommandNotFoundException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s'는 유효한 커멘드가 아닙니다."
    }
}
