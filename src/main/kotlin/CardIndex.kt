class CardIndex(private var index: Int = 0, private val maxIndex: Int) {

    fun getIndex(): Int {
        return index
    }

    fun increaseIndex() {
        index++
        validateIndex()
    }

    private fun validateIndex() {
        require(index < maxIndex) { ERR_MSG_EMPTY_CARD }
    }

    companion object {
        private const val ERR_MSG_EMPTY_CARD = "카드가 모두 소진되었습니다."
    }
}
