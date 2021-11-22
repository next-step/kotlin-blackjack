package blackject.model

@JvmInline
value class Amount(private val money: Double = MIN_AMOUNT) {

    companion object {
        const val MIN_AMOUNT = 0.0
    }
}
