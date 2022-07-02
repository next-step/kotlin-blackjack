package blackjack.domain.card

/**
 * 카드 데이터 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
sealed class Card(val type: CardType, val score: Int)

class Ace(type: CardType) : Card(type, 1)

class Two(type: CardType) : Card(type, 2)

class Three(type: CardType) : Card(type, 3)

class Four(type: CardType) : Card(type, 4)

class Five(type: CardType) : Card(type, 5)

class Six(type: CardType) : Card(type, 6)

class Seven(type: CardType) : Card(type, 7)

class Eight(type: CardType) : Card(type, 8)

class Nine(type: CardType) : Card(type, 9)

class Jack(type: CardType) : Card(type, 10)

class Queen(type: CardType) : Card(type, 10)

class King(type: CardType) : Card(type, 10)
