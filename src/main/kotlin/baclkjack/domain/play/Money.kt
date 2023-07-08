package baclkjack.domain.play


@JvmInline
value class Money(val value: Int){
    init {
        require(value > BASE_MONEY){
            "배팅 금액은 $BASE_MONEY 보다 커야 합니다."
        }
    }

    companion object {
        const val BASE_MONEY = 0
    }
}
