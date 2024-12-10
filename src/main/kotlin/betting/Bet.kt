package betting

@JvmInline
value class Bet(
    val amount: Double = 0.0,
) {
    init {
        require(amount >= 0) { "베팅 금액은 음수일 수 없습니다." }
    }

    fun negative(): Double = amount * -1L
}
