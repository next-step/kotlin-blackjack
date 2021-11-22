package blackjack.error

class InvalidMapToPlayStateException(element: String) : RuntimeException() {
    override val message = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s' 타입은 특정 플레이 상태로 전환이 불가능합니다"
    }
}
