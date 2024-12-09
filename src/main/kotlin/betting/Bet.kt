package betting

@JvmInline
value class Bet(
    val amount: Long = 0L,
) {
    init {
        require(amount >= 0) { "베팅 금액은 음수일 수 없습니다." }
    }
}
