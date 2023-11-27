class GameIndex(private val maxIndex: Int) {

    private var _index: Int = 0
    val index: Int
        get() = _index

    fun increaseIndex() {
        _index++
        validateIndex()
    }

    private fun validateIndex() {
        require(_index < maxIndex) { ERR_MSG_EMPTY_CARD }
    }

    companion object {
        private const val ERR_MSG_EMPTY_CARD = "카드가 모두 소진되었습니다."
    }
}
