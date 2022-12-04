package model

class PokerNumberFinder(private val desc: String) {
    val pokerNumber: PokerNumber =
        PokerNumber.values().find { it.desc == desc }
            ?: throw IllegalArgumentException("해당 원소를 찾을 수 없습니다")
}
