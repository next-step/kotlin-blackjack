class GameIndex(private val maxIndex: Int) {

    private var _cardIndex: Int = 0
    val cardIndex: Int
        get() = _cardIndex

    fun increaseIndex() {
        _cardIndex++
        validateIndex()
    }

    private fun validateIndex() {
        require(_cardIndex < maxIndex) { ERR_MSG_EMPTY_CARD }
    }

    companion object {
        private const val ERR_MSG_EMPTY_CARD = "카드가 모두 소진되었습니다."
    }
}
