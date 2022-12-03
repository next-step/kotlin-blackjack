package model

class Card(
    val pokerNumber: PokerNumber,
    val pokerShape: PokerShape = PokerShape.pokerShapes().first()
)
