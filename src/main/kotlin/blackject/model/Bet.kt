package blackject.model

/**
 * 반환 금액 관리하는 클래스
 * */
class Bet(
    private val amount: Amount
) {
    fun isLose(): Double = amount.unaryMinus()

    fun winner(): Double = amount.value

    fun isAllBlackJack(): Double = amount.value

    fun blackJack(): Double = amount.value * BLACK_JACK_MULTIPLE

    companion object {
        private const val BLACK_JACK_MULTIPLE = 1.5
    }
}
