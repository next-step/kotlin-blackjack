package blackject.model

/**
 * 배팅 금액 관리 클래스
 * */
data class Amount(private var value: Double = MIN_AMOUNT) {

    companion object {
        const val MIN_AMOUNT = 0.0
    }
}
