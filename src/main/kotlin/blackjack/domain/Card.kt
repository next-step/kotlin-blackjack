package blackjack.domain

private const val VALUE_ACE = "A"
private const val AlPHABET_DEFAULT_POINT = 10
private const val ACE_POINT = 1

data class Card(val suit: SuitType, val value: String) {
    fun getPoint(aceToBig: Boolean = true): Int {
        return try {
            value.toInt()
        } catch (e: NumberFormatException) {
            if (value == VALUE_ACE) {
                if (aceToBig) ACE_POINT + AlPHABET_DEFAULT_POINT else ACE_POINT
            } else {
                AlPHABET_DEFAULT_POINT
            }
        }
    }
}
