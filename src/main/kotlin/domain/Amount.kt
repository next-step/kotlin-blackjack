package domain

data class Amount(private val value: Int) {
    init {
        require(value >= 0) { "금액은 음수가 될 수 없습니다." }
    }

    fun amount(): Int {
        return value
    }
}
