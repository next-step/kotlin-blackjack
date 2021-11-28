package blackjack.error

class InvalidMoneyRangeException(element: Int) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s'는 돈의 유효한 범위가 아닙니다"
    }
}
