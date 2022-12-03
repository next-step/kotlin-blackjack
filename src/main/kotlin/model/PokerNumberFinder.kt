package model

class PokerNumberFinder(private val value: String) {
    val pokerNumber: PokerNumber =
        PokerNumber.pokerNumbers().find { it.value == value }
            ?: throw IllegalArgumentException("해당 원소를 찾을 수 없습니다")
}
