package blackjack.error

class InvalidDrawException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s' 타입은 카드를 추가할 수 없습니다"
    }
}
