package blackjack.domain

class Card(private val character: Char, val shape: String) {
    init {
        require(VALID_CHARACTER.contains(character)) { "유효한 카드 문자가 아닙니다." }
    }

    companion object {
        private val VALID_CHARACTER = setOf('A', 'J', 'Q', 'K', '2', '3', '4', '5', '6', '7', '8', '9')
    }
}
