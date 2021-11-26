package blackjack.error

class InvalidCalculateScoreException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s' 타입은 스코어를 계산할 수 없습니다"
    }
}
