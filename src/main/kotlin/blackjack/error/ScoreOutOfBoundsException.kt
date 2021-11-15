package blackjack.error

class ScoreOutOfBoundsException(element: Int) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s'는 스코어의 범위를 벗어난 값 입니다."
    }
}
