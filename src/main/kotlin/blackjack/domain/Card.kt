package blackjack.domain

/**
 * 카드 데이터 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
sealed class Card(val type: CardType, val score: Int)

class ACE(type: CardType) : Card(type, 1)

class TWO(type: CardType) : Card(type, 2)

class THREE(type: CardType) : Card(type, 3)

class FOUR(type: CardType) : Card(type, 4)

class FIVE(type: CardType) : Card(type, 5)

class SIX(type: CardType) : Card(type, 6)

class SEVEN(type: CardType) : Card(type, 7)

class EIGHT(type: CardType) : Card(type, 8)

class NINE(type: CardType) : Card(type, 9)

class JACK(type: CardType) : Card(type, 10)

class QUEEN(type: CardType) : Card(type, 10)

class KING(type: CardType) : Card(type, 10)
