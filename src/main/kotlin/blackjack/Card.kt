package blackjack

const val VALUE_ACE = "A"
const val AlPHABET_DEFAULT_POINT = 10
const val ACE_POINT = 1

class Card(private val suit: SuitType, private val value: String) {
    fun getPoint(aceToBig: Boolean): Int {
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
