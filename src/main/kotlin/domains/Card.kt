package domains

data class Card(val pokerNumber: PokerNumber, val pokerShape: PokerShape) {
    override fun toString(): String {
        return pokerNumber.displayName + pokerShape.displayName
    }
}
