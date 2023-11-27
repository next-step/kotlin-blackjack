class GameIndex(private val maxCardIndex: Int) {

    private var _cardIndex: Int = 0
    val cardIndex: Int
        get() = _cardIndex

    private var _playerIndex: Int = 0
    val playerIndex: Int
        get() = _playerIndex

    fun increaseCardIndex() {
        _cardIndex++
        validateIndex()
    }

    fun increasePlayerIndex() {
        _playerIndex++
    }

    private fun validateIndex() {
        require(_cardIndex < maxCardIndex) { ERR_MSG_EMPTY_CARD }
    }

    companion object {
        private const val ERR_MSG_EMPTY_CARD = "카드가 모두 소진되었습니다."
    }
}
